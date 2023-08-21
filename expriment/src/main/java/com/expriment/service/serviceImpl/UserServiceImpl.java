package com.expriment.service.serviceImpl;

import com.expriment.entity.UserEntity;
import com.expriment.entity.vo.cIdString;
import com.expriment.service.UserService;
import com.expriment.utils.audit.LoggerClass;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

@Service
public class UserServiceImpl implements UserService {

    public static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    UserEntity[] lists={new UserEntity(1111L,"Indradev","950783892"),
                    new UserEntity(1112L,"dev","950745892")};

    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    @Override
    public UserEntity getUser(Long id){
        return Arrays.stream(lists).filter(userEntity -> userEntity.getUserId().equals(id)).findAny().orElse(null);
//                lists.stream().filter(userEntity -> userEntity.getUserId().equals(id)).findAny().orElse(null);
    }

    @Override
    public boolean validateString(String str) throws IOException {
//        String s="()]";

        cIdString cIdStringVal = objectMapper.readValue(str, cIdString.class);
        String s= cIdStringVal.getPassedString();
//        if(s.length() %2 != 0) return  false;
        Stack<Character> stack = new Stack<>();

        for(int i=0; i<s.length(); i++){
            char ch= s.charAt(i);
            LoggerClass.appLayerLogger.info(stack);
            if(ch == ')' && !stack.empty() && stack.peek()=='('){
                stack.pop();
            }else if(ch == '}' && !stack.empty() && stack.peek()=='{'){
                stack.pop();
            }else if(ch == ']' && !stack.empty() && stack.peek()=='['){
                stack.pop();
            }else {
//                if(!Character.isAlphabetic(ch))stack.push(ch);
//                if(ch =='(' || ch =='{' || ch == ']')
                    stack.push(ch);
            }

        } return stack.isEmpty();
/*            Stack<Character> stac =new Stack<>();
            for(int i=0; i<s.length(); i++){
                char ch = s.charAt(i);
//                if(i==0)  stac.push(ch);
                if(ch== ')'){
                    if(stac.peek()=='('){
                        stac.pop();
                    }else {
                        return false;
                    }
                }else if(stac.peek()=='}'){
                    if(stac.peek()=='{'){
                        stac.pop();
                    }else {
                        return false;
                    }
                }else if(stac.peek()==']'){
                    if(stac.peek()=='['){
                        stac.pop();
                    }else {
                        return false;
                    }
                }else{
                    stac.push(ch);
                }

            }
            if(stac.size()==0){
                return true;
            }
            return false;*/

        }

    public boolean validateStringList(String str) throws IOException {
        // not working porper this method
        cIdString cIdStringVal = objectMapper.readValue(str, cIdString.class);
        String s= cIdStringVal.getPassedString();
//        if(s.length() %2 != 0) return  false;
//        Stack<Character> stack = new Stack<>();
//        return Arrays.stream(lists).filter(userEntity -> userEntity.getUserId().equals(id)).findAny().orElse(null);

        List<Character> list = new ArrayList<>();
        List<Character> list1 = new ArrayList<>();
        List<Character> cont = new ArrayList<>();
        String st ="(){}[]";
        for(int i=0; i< 6; i++){
            cont.add(st.charAt(i));
        }
        for(int i=0; i<s.length(); i++){
            char ch= s.charAt(i);
            LoggerClass.appLayerLogger.info(list);
            if((ch == ')' || ch == '}' ||ch == ']') && !list.isEmpty()){
                list1.add(ch);
                if(list.contains('(') && ch == ')'){
                    list.remove(list.indexOf('('));
                }else  if(list.contains('{') && ch == '}'){
                    list.remove(list.indexOf('{'));
                }else if(list.contains('[') && ch == ']'){
                    list.remove(list.indexOf('['));
                }
//                else if ((ch == ')' || ch == '}' ||ch == ']')){
//
//                }
//                list.pop();
//            }else if(ch == '}' && !list.empty() && list.peek()=='{'){
//                list.pop();
//            }else if(ch == ']' && !list.empty() && list.peek()=='['){
//                list.pop();
//            }else {
////                if(!Character.isAlphabetic(ch))list.push(ch);
////                if(ch =='(' || ch =='{' || ch == ']')
//                list.push(ch);
            }else if(ch == '(' || ch == '{' ||ch == '['){
                list.add(ch);
                if(!list1.isEmpty() && list1.contains(ch))list1.remove(ch);
            }

        }
        list1.remove(0);
        return !list.containsAll(cont) && list1.isEmpty();

    }
}
