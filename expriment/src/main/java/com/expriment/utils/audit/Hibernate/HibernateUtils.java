package com.expriment.utils.audit.Hibernate;

import com.expriment.utils.audit.LoggerClass;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author SAIDULU (EMPID:061)
 * @version 1.0
 * 01-Jun-2016
 */
@Component
public class HibernateUtils/* extends OLPExceptionHandler*/ {
    Logger logger =  LogManager.getLogger("HibernateUtils");
    ObjectMapper objectMapper= new ObjectMapper();
    @Autowired
    public SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        try {

            SessionFactory sessionFactory = getSessionFactory();
			/*if(UtilityConstants.INTERCEPTORS.ISHIBERNATEINTERCEPTORENABLED)
				return sessionFactory.withOptions().interceptor(auditLogInterceptor).openSession();
			else*/
            return sessionFactory.openSession();
            //logger.info("getSession() : Hibernate Session Object ->> "+session);
            //return session;
        }catch(Exception e) {
            LoggerClass.dataloaderLogger.error("(:) Error While Getting Session Object (:)");
            e.printStackTrace();
        }
        return null;
    }

    public Session openSession() {

        try {

            SessionFactory sessionFactory = getSessionFactory();
            return sessionFactory.openSession();
        }catch(Exception e) {
            LoggerClass.dataloaderLogger.error("(:) Error While Getting Session Object (:) in openSession()");
            e.printStackTrace();
        }
        return null;

    }

    public void closeSession(Session session) {
        //LoggerClass.dataloaderLogger.info("closeSession() : Session Object ->> "+session);
        if(session != null) {
            session.close();
        }
    }

    public <T> T saveEntity(T entity) {

        try {
            LoggerClass.dataloaderLogger.info("saveEntity() : Entity Class ->> "+objectMapper.writeValueAsString(entity).substring(100));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Session session = null;
        Transaction tx = null;
        try {
            session = getSession();
            tx = session.beginTransaction();
            session.save(entity);
            tx.commit();
            return entity;

        }catch(Exception e) {
            LoggerClass.dataloaderLogger.error("(:) Error in SaveEntity Method (:) ");
            e.printStackTrace();
            if(tx != null )
                tx.rollback();
        }finally {
            closeSession(session);
        }
        return null;
    }

    public <T> T updateEntity(T entity) {

        LoggerClass.dataloaderLogger.info("updateEntity() : Entity Class ->> "+entity.getClass());
        Transaction tx =null;
        Session session = null;
        try {
            session = getSession();
            tx = session.beginTransaction();
            session.update(entity);
            tx.commit();
            return entity;

        }catch(Exception e) {
            LoggerClass.dataloaderLogger.error("(:) Error in UpdateEntity Method (:) ");
            e.printStackTrace();
            if( tx != null )
                tx.rollback();
        }finally {
            closeSession(session);
        }
        return null;
    }

    public <T> T saveOrUpdateEntity(T entity) {

        try {
            LoggerClass.dataloaderLogger.info("saveOrUpdateEntity() : Entity Class ->> "+ objectMapper.writeValueAsString(entity).substring(0,100));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Transaction tx = null;
        Session session = null;
        try {
            session = getSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(entity);
            tx.commit();
            return entity;

        }catch(Exception e) {
            LoggerClass.dataloaderLogger.error("(:) Error in SaveOrUpdateEntity Method (:) ");
            e.printStackTrace();
            if( tx != null )
                tx.rollback();
        }finally {
            closeSession(session);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public <T> T deleteEntity(T entity, Serializable primaryId) {

        LoggerClass.dataloaderLogger.info("deleteEntity() : Entity Class ->> "+entity.getClass());
        Transaction tx = null;
        Session session = null;
        try {
            session = getSession();
            tx = session.beginTransaction();
            Object dataObject = session.get(entity.getClass(),primaryId);
            session.delete((T)dataObject);
            tx.commit();
            return entity;

        }catch(Exception e) {
            LoggerClass.dataloaderLogger.error("(:) Error in DeleteEntity Method (:) ");
            if( tx != null )
                tx.rollback();
            e.printStackTrace();
        }finally {
            closeSession(session);
        }
        return null;
    }

    public String deleteEntityByHQL(String hqlQuery) {

        LoggerClass.dataloaderLogger.info("deleteEntityByCriteria() : HQL Query ->> "+hqlQuery);

        Session session = null;
        try {
            session = getSession();
            Query query = session.createQuery(hqlQuery);
            query.executeUpdate();
            return "SUCCESS"; //TODO : Need to uncomment above line
        }catch(Exception e) {
            LoggerClass.dataloaderLogger.error("(:) Error in deleteEntityByHQL Method (:)");
            e.printStackTrace();
        }
        finally {
            closeSession(session);
        }
        return null;
    }

    public String deleteEntityByHQL(String hqlQuery,String setParamName, Collection setParamValue) {

        LoggerClass.dataloaderLogger.info("deleteEntityByCriteria() : HQL Query ->> "+hqlQuery+" set PropertyName"+setParamName+" setParamValues"+setParamValue);

        Session session = null;
        try {
            session = getSession();
            Query query = session.createQuery(hqlQuery);
            query.setParameterList(setParamName, setParamValue);
            query.executeUpdate();
            return "SUCCESS"; //TODO : Need to uncomment above line
        }catch(Exception e) {
            LoggerClass.dataloaderLogger.error("(:) Error in deleteEntityByHQL Method (:)");
            e.printStackTrace();
        }
        finally {
            closeSession(session);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public <T> T findEntityById(Class<T> entityClass,Serializable primaryId) {

        LoggerClass.dataloaderLogger.info("getEntityById() : Entity Class ->> "+entityClass+" Primary ID ->>"+primaryId);

        Session session = null;
        try {
            session = getSession();
            Object dataObject = session.get(entityClass, primaryId);
//            LoggerClass.dataloaderLogger.info("Welcome jobs started.{\"status\":\"ok\",\"totalResults\":34,\"articles\":[{\"source\":{\"id\":null,\"name\":\"CBS Sports\"},\"author\":\"Josh Edwards\",\"title\":\"2023 NFL Draft tracker, live: Day 3 picks by team, grades, analysis, order, projections - CBS Sports\",\"description\":\"See how things transpired on Day 2 of the 2023 NFL Draft and look ahead to Day 3\",\"url\":\"https://www.cbssports.com/nfl/news/2023-nfl-draft-tracker-live-day-3-picks-by-team-grades-analysis-order-projections/live/\",\"urlToImage\":\"https://sportshub.cbsistatic.com/i/r/2023/04/29/bea40429-a1f7-4458-b579-97ddb7f680b9/thumbnail/1200x675/8a1b3ddbca956bda2de46e469b9c1fec/thepickisin-ko.png\",\"publishedAt\":\"2023-04-29T18:02:40Z\",\"content\":\"Jaren Hall, QB, BYU (#3)6-foot, 207 Arm length: 29 3/4\\\" Hand size: 9 1/2\\\" \\r\\n*Originally from Spanish Fork, Utah. Hall was rated a .8549 on a scale to 1.000 by the 247Sports Composite, which equated t… [+367 chars]\"},{\"source\":{\"id\":null,\"name\":\"ESPN\"},\"author\":\"Tim Bontemps\",\"title\":\"Doc Rivers - Joel Embiid likely doubtful for G1 vs. Celtics - ESPN - ESPN\",\"description\":\"76ers coach Doc Rivers said that if he \\\"was a betting man,\\\" Joel Embiid will be doubtful to play in \\\"at least\\\" Monday's Game 1 against the Celtics in TD Garden.\",\"url\":\"https://www.espn.com/nba/story/_/id/36321151/doc-rivers-joel-embiid-likely-doubtful-g1-vs-celtics\",\"urlToImage\":\"https://a3.espncdn.com/combiner/i?img=%2Fphoto%2F2023%2F0422%2Fr1162585_1296x729_16%2D9.jpg\",\"publishedAt\":\"2023-04-29T17:57:11Z\",\"content\":\"CAMDEN, N.J. -- Philadelphia 76ers coach Doc Rivers said Saturday afternoon that if he \\\"was a betting man,\\\" superstar center Joel Embiid will be doubtful to play in \\\"at least\\\" Monday's Game 1 of the … [+3715 chars]\"},{\"source\":{\"id\":null,\"name\":\"9to5google.com\"},\"author\":\"Ben Schoon\",\"title\":\"Pixel Fold display shown off in latest leak - 9to5Google\",\"description\":\"The Google Pixel Fold is set to be unveiled in just over a week, and ahead of the reveal we’re...\",\"url\":\"https://9to5google.com/2023/04/29/pixel-fold-display-leak/\",\"urlToImage\":\"https://i0.wp.com/9to5google.com/wp-content/uploads/sites/4/2023/04/google-pixel-fold-inner-display-2.jpg?resize=1200%2C628&quality=82&strip=all&ssl=1\",\"publishedAt\":\"2023-04-29T17:50:00Z\",\"content\":\"The Google Pixel Fold is set to be unveiled in just over a week, and ahead of the reveal we’re getting more and more details about the company’s first foldable. Now, an official-looking image of the … [+2095 chars]\"},{\"source\":{\"id\":null,\"name\":\"WPVI-TV\"},\"author\":null,\"title\":\"Philadelphia Eagles trade with Detroit Lions for RB D'Andre Swift - WPVI-TV\",\"description\":\"The trade is a homecoming for Swift, who is from Philadelphia and went to high school at St. Joseph's Prep before attending college at Georgia.\",\"url\":\"https://6abc.com/philadelphia-eagles-deandre-swift-rb-detroit-lions-nfl-2023/13195677/\",\"urlToImage\":\"https://cdn.abcotvs.com/dip/images/13195646_dandre-swift.jpg?w=1600\",\"publishedAt\":\"2023-04-29T17:34:38Z\",\"content\":\"PHILADELPHIA -- The Detroit Lions are trading running back D'Andre Swift to the Philadelphia Eagles, sources told ESPN's Adam Schefter on Saturday.\\r\\nA source told Schefter that the Lions are getting … [+562 chars]\"},{\"source\":{\"id\":null,\"name\":\"ESPN\"},\"author\":\"Tim McManus\",\"title\":\"Eagles select fifth Georgia defender over 2 NFL drafts - ESPN - ESPN\",\"description\":\"The Eagles selected Georgia CB Kelee Ringo in the third round, bringing the total to five UGA starters picked by Philly over the last two NFL drafts.\",\"url\":\"https://www.espn.com/nfl/story/_/id/36320912/eagles-select-fifth-georgia-defender-last-2-nfl-drafts\",\"urlToImage\":\"https://a3.espncdn.com/combiner/i?img=%2Fphoto%2F2023%2F0429%2Fr1166061_1296x729_16%2D9.jpg\",\"publishedAt\":\"2023-04-29T17:22:45Z\",\"content\":\"PHILADELPHIA -- Another day, another Dawg.\\r\\nThe Philadelphia Eagles continue to load up on players from the Georgia Bulldogs' dominant defense in the NFL draft. They selected defensive tackle Jalen C… [+2805 chars]\"},{\"source\":{\"id\":\"cnn\",\"name\":\"CNN\"},\"author\":\"Jennifer Hansler\",\"title\":\"First US government-organized convoy evacuates private American citizens from Sudan conflict - CNN\",\"description\":\"The first US-led effort to evacuate private American citizens from the conflict in Sudan was completed Saturday, with a convoy organized by the US government reaching Port Sudan after a long journey from Khartoum.\",\"url\":\"https://www.cnn.com/2023/04/29/politics/american-citizens-sudan-convoy-evacuation/index.html\",\"urlToImage\":\"https://media.cnn.com/api/v1/images/stellar/prod/230429115656-01-sudan-clashes-0428.jpg?c=16x9&q=w_800,c_fill\",\"publishedAt\":\"2023-04-29T17:19:00Z\",\"content\":\"The first US-led effort to evacuate private American citizens from the conflict in Sudan was completed Saturday, with a convoy organized by the US government reaching Port Sudan after a long journey … [+4855 chars]\"},{\"source\":{\"id\":null,\"name\":\"NDTV News\"},\"author\":null,\"title\":\"Prince William, Kate Middleton Celebrate 12 Years Of Marriage, Share Unseen Pic - NDTV\",\"description\":\"The picture was taken last year at the family's Sandringham Estate in Norfolk, England.\",\"url\":\"https://www.ndtv.com/world-news/prince-william-kate-middleton-celebrate-12-years-of-marriage-share-unseen-pic-3991450\",\"urlToImage\":\"https://c.ndtvimg.com/2023-04/63hvm8ig_12-years-reads-the-instagram-caption-for-the-picture-_625x300_29_April_23.jpg\",\"publishedAt\":\"2023-04-29T16:58:30Z\",\"content\":\"\\\"12 years,\\\" reads the Instagram caption for the picture\\r\\nThe Prince and Princess of Wales, today took to social media to share an unseen picture of them during a bike ride with their arms around each… [+1307 chars]\"},{\"source\":{\"id\":null,\"name\":\"Variety\"},\"author\":\"J. Kim Murphy\",\"title\":\"Box Office: ‘Are You There God? It’s Me, Margaret’ Can’t Topple ‘Super Mario Bros.’ - Variety\",\"description\":\"Are you there box office? It’s-a me, “Mario.” Now in its fourth weekend of release, “The Super Mario Bros. Movie” is still dominating the competition on domestic chart…\",\"url\":\"https://variety.com/2023/film/box-office/super-mario-bros-beats-are-you-there-god-its-me-margaret-opening-1235598446/\",\"urlToImage\":\"https://variety.com/wp-content/uploads/2022/06/Screen-Shot-2023-01-12-at-11.09.16-AM.png?w=1000&h=563&crop=1\",\"publishedAt\":\"2023-04-29T16:16:00Z\",\"content\":\"Are you there box office? It’s-a me, “Mario.” \\r\\nNow in its fourth weekend of release, “The Super Mario Bros. Movie” is still dominating the competition on domestic charts, fending off theatrical newc… [+4076 chars]\"},{\"source\":{\"id\":\"bloomberg\",\"name\":\"Bloomberg\"},\"author\":\"Hannah Levitt, Katanga Johnson\",\"title\":\"FDIC Asks Banks for Final First Republic Bids Due Sunday - Bloomberg\",\"description\":\"The Federal Deposit Insurance Corp. has asked banks including JPMorgan Chase & Co. and PNC Financial Services Group Inc. to submit final bids for First Republic Bank by Sunday after gauging initial interest earlier in the week, according to people with knowle…\",\"url\":\"https://www.bloomberg.com/news/articles/2023-04-29/fdic-asks-jpmorgan-pnc-for-final-first-republic-bids-due-sunday\",\"urlToImage\":\"https://assets.bwbx.io/images/users/iqjWHBFdfxIU/ibBzKmtnUzIU/v1/1200x800.jpg\",\"publishedAt\":\"2023-04-29T16:11:40Z\",\"content\":\"The Federal Deposit Insurance Corp. has asked banks including \\r\\nJPMorgan Chase &amp; Co. and \\r\\nPNC Financial Services Group Inc. to submit final bids for \\r\\nFirst Republic Bank by Sunday after gauging… [+451 chars]\"},{\"source\":{\"id\":\"reuters\",\"name\":\"Reuters\"},\"author\":null,\"title\":\"Air strikes hit Khartoum, envoy sees sides more open to talks - Reuters.com\",\"description\":\"Air strikes and artillery rocked Khartoum on Saturday as Sudan entered a third week of fighting between rival military forces despite a ceasefire, prompting more civilians to flee and renewed warnings of wider instability if the war is not stopped.\",\"url\":\"https://www.reuters.com/world/africa/airstrikes-artillery-continue-sudan-fighting-enters-third-week-2023-04-29/\",\"urlToImage\":\"https://www.reuters.com/resizer/WJpR-nNhkQyMvqMVixfZUnF5kKg=/795x416/smart/filters:quality(80)/cloudfront-us-east-2.images.arcpublishing.com/reuters/3EZSNCV6FNPELPA3CDPCZIVY4M.jpg\",\"publishedAt\":\"2023-04-29T15:54:00Z\",\"content\":\"KHARTOUM, April 29 (Reuters) - Air strikes and artillery rocked Khartoum on Saturday as Sudan entered a third week of fighting between rival military forces despite a ceasefire, prompting more civili… [+5591 chars]\"},{\"source\":{\"id\":null,\"name\":\"BBC News\"},\"author\":\"https://www.facebook.com/bbcnews\",\"title\":\"Laura Kuenssberg: What could go right and wrong for parties in England's local elections? - BBC\",\"description\":\"Laura Kuenssberg asks what could go right and wrong for political parties at the polls next week.\",\"url\":\"https://www.bbc.com/news/uk-65429598\",\"urlToImage\":\"https://ichef.bbci.co.uk/news/1024/branded_news/1081E/production/_129541676_6smlk_index_template_laura.jpg\",\"publishedAt\":\"2023-04-29T15:30:18Z\",\"content\":\"With the elections for thousands of councillors in England on Thursday MPs, councillors and their hardy activists have been knocking on voters' doors over the last few weeks. \\r\\nForget about the polit… [+8369 chars]\"},{\"source\":{\"id\":null,\"name\":\"BBC News\"},\"author\":\"https://www.facebook.com/bbcnews\",\"title\":\"Sudan crisis risks becoming a nightmare for the world - former PM Hamdok - BBC\",\"description\":\"Former PM Abdalla Hamdok says the Sudan conflict could become worse than the wars in Syria and Libya.\",\"url\":\"https://www.bbc.com/news/world-africa-65436815\",\"urlToImage\":\"https://ichef.bbci.co.uk/news/1024/branded_news/BDC8/production/_129548584_mediaitem129548510.jpg\",\"publishedAt\":\"2023-04-29T15:05:34Z\",\"content\":\"The former prime minister of Sudan has warned that the conflict in his country could become worse than those in Syria and Libya. \\r\\nAbdalla Hamdok said the fighting will be a \\\"nightmare for the world\\\"… [+4398 chars]\"},{\"source\":{\"id\":\"cnn\",\"name\":\"CNN\"},\"author\":\"Faith Karimi\",\"title\":\"'Mom, these bad men have me': She believes scammers cloned her daughter's voice in a fake kidnapping - CNN\",\"description\":\"Jennifer DeStefano's phone rang in January with a terrifying call from her sobbing 15-year-old daughter, saying she'd been kidnapped. But as a relieved DeStefano soon learned, her daughter was safe and the call was a scam.\",\"url\":\"https://www.cnn.com/2023/04/29/us/ai-scam-calls-kidnapping-cec/index.html\",\"urlToImage\":\"https://media.cnn.com/api/v1/images/stellar/prod/230421133524-02-phoenix-kidnapping-scam.jpg?c=16x9&q=w_800,c_fill\",\"publishedAt\":\"2023-04-29T13:26:00Z\",\"content\":\"Jennifer DeStefanos phone rang one afternoon as she climbed out of her car outside the dance studio where her younger daughter Aubrey had a rehearsal. The caller showed up as unknown, and she briefly… [+10585 chars]\"},{\"source\":{\"id\":null,\"name\":\"CNBC\"},\"author\":\"Michael Wayland\",\"title\":\"Why GM is killing the Chevy Bolt — America's cheapest EV — amid record sales - CNBC\",\"description\":\"To reach stated EV goals, GM needs the production capacity, profits and market positioning of its next-generation EVs. It doesn't believe it needs the Bolt.\",\"url\":\"https://www.cnbc.com/2023/04/29/why-gm-is-killing-the-chevy-bolt-ev-amid-record-sales.html\",\"urlToImage\":\"https://image.cnbcfm.com/api/v1/image/107046644-1649882743073-bolt_IMG_1304.jpg?v=1682774422&w=1920&h=1080\",\"publishedAt\":\"2023-04-29T13:20:22Z\",\"content\":\"A Chevrolet Bolt EUV on display at the New York Auto Show, April 13, 2022.\\r\\nDETROIT After years of lackluster performance and a fire-provoked recall, the all-electric Chevrolet Bolt EV was finally ga… [+7035 chars]\"},{\"source\":{\"id\":\"cnn\",\"name\":\"CNN\"},\"author\":\"Josh Pennington,Alex Stambaugh,Kostan Nechyporenko,Mariya Knight,Darya Tarasova\",\"title\":\"Suspected drone strike triggers massive fire in Russian-occupied Crimea - CNN\",\"description\":\"A suspected drone strike has sparked a huge fire at a fuel storage facility in the Russian-occupied Crimean port city of Sevastopol.\",\"url\":\"https://www.cnn.com/2023/04/29/europe/fuel-tank-sevastopol-crimea-hnk-intl/index.html\",\"urlToImage\":\"https://media.cnn.com/api/v1/images/stellar/prod/230429034744-02-sevastopol-fire-042923.jpg?c=16x9&q=w_800,c_fill\",\"publishedAt\":\"2023-04-29T13:16:00Z\",\"content\":\"A suspected drone strike has sparked a huge fire at a fuel storage facility in the Russian-occupied Crimean port city of Sevastopol.\\r\\nThe Russian-backed governor of the city, Mikhail Razvozhaev, said… [+1221 chars]\"},{\"source\":{\"id\":null,\"name\":\"SciTechDaily\"},\"author\":null,\"title\":\"Sticky Situation: Critical Antenna on ESA's Jupiter Icy Moons Explorer Fails To Deploy - SciTechDaily\",\"description\":\"ESA's Jupiter Icy Moons Explorer (Juice) has encountered a deployment issue with its Radar for Icy Moons Exploration (RIME) antenna. The issue, potentially caused by a stuck pin, is being addressed by ESA's mission control teams with various strategies, inclu…\",\"url\":\"https://scitechdaily.com/?p=272800\",\"urlToImage\":\"https://scitechdaily.com/images/Illustration-of-JUICE-Spacecraft-at-Jupiter-scaled.jpg\",\"publishedAt\":\"2023-04-29T12:07:15Z\",\"content\":\"ByEuropean Space Agency (ESA)April 29, 2023\\r\\nIllustration of the JUICE spacecraft at Jupiter. The Radar for Icy Moons Exploration (RIME) antenna on ESAs Jupiter Icy Moons Explorer (Juice) spacecraft … [+4975 chars]\"},{\"source\":{\"id\":null,\"name\":\"CNET\"},\"author\":null,\"title\":\"I Felt the Motorola Rizr's Rollable Screen Move Under My Hand - CNET\",\"description\":\"Motorola's wild concept phone is, for all intents and purposes, a functional smartphone.\",\"url\":\"https://www.cnet.com/tech/mobile/i-felt-the-motorola-rizrs-rollable-screen-move-under-my-hand/\",\"urlToImage\":\"https://www.cnet.com/a/img/resize/aececfb8dc99f41b34651913d2447cf819e6bfbf/hub/2023/04/28/d4bd8640-fe7a-4c16-a3ad-5df3be393940/lede-web.jpg?auto=webp&fit=crop&height=675&width=1200\",\"publishedAt\":\"2023-04-29T12:00:00Z\",\"content\":\"At Mobile World Congress in February, Motorola showed off its rollable concept phone, which was the first time the public got to see a working version of the next kind of flexible display for consume… [+7944 chars]\"},{\"source\":{\"id\":\"abc-news\",\"name\":\"ABC News\"},\"author\":\"Jon Haworth, Jessie DiMartino, Cristina Corujo\",\"title\":\"5 dead in Texas shooting, suspect armed with AR-15 is on the loose - ABC News\",\"description\":\"All of the victims were shot from the neck up “almost execution style.”\",\"url\":\"https://abcnews.go.com/US/5-dead-texas-shooting-suspect-armed-ar-15/story?id=98957271\",\"urlToImage\":\"https://s.abcnews.com/images/US/police-01-as-gty-190813_hpMain_16x9_992.jpg\",\"publishedAt\":\"2023-04-29T11:36:26Z\",\"content\":\"Five people are dead after being shot in a home by a suspect armed with AR-15 style rifle, police say.\\r\\nThe incident occurred at approximately 11:31 p.m. local time when officials from the San Jacint… [+3922 chars]\"},{\"source\":{\"id\":\"cnn\",\"name\":\"CNN\"},\"author\":\"Nathaniel Meyersohn\",\"title\":\"What Bed Bath & Beyond, Toys 'R' Us and RadioShack have in common - CNN\",\"description\":\"In the 1980s, a new type of specialty retail chain started to emerge: \\\"category killers.\\\"\",\"url\":\"https://www.cnn.com/2023/04/29/business/bed-bath-beyond-toys-r-us-category-killer-retail/index.html\",\"urlToImage\":\"https://media.cnn.com/api/v1/images/stellar/prod/230424145208-bed-bath-beyond-0904-file.jpg?c=16x9&q=w_800,c_fill\",\"publishedAt\":\"2023-04-29T10:05:00Z\",\"content\":\"In the 1980s, a new type of specialty retail chain started to emerge: category killers.\\r\\nThe stores powerhouse business model was aimed at giving shoppers access to every different size, style and co… [+6958 chars]\"},{\"source\":{\"id\":null,\"name\":\"INSIDER\"},\"author\":\"Isobel van Hagen\",\"title\":\"A Barcelona seafood restaurant got a mysterious booking. Then Obama, Spielberg, and Springsteen came to dinner. - Yahoo News\",\"description\":\"The famous trio was in Barcelona for a Friday night Springsteen concert. At the Amar restaurant, they dined on oysters, shellfish, and caviar.\",\"url\":\"https://www.insider.com/obama-spielberg-and-springsteen-surprise-staff-at-barcelona-restaurant-2023-4\",\"urlToImage\":\"https://s.yimg.com/ny/api/res/1.2/1JsFmxnfRU5g7NI12z74uw--/YXBwaWQ9aGlnaGxhbmRlcjt3PTEyMDA7aD05MDA-/https://media.zenfs.com/en/insider_articles_922/8163674897824fd50230b8459fc7bd69\",\"publishedAt\":\"2023-04-29T09:54:39Z\",\"content\":\"Former US President Barack Obama, film director Steven Spielberg and singer Bruce Springsteen pose for a picture with Amar restaurant staff in Barcelona, Spain April 27, 2023.Pol Perello Franch via R… [+2253 chars]\"}]}");
            return (T) dataObject;
        }catch(Exception e) {
            LoggerClass.dataloaderLogger.error("(:) Error in getEntityById Method (:)");
            e.printStackTrace();
        }
        finally {
            closeSession(session);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public <T> T findEntityByCriteria(Class<T> entityClass,String primaryPropertyName, Serializable primaryId) {

        LoggerClass.dataloaderLogger.info("getEntityByCriteria() : Entity Class ->> "+entityClass+" PrimaryPropertyName ->> "+primaryPropertyName+" Primary ID ->>"+primaryId);

        Session session = null;
        try {
            session = getSession();
            Object dataObject =  session.createCriteria(entityClass)
                    .add(Restrictions.eq(primaryPropertyName, primaryId))
                    .uniqueResult();
            return (T) dataObject;
        }catch(Exception e) {
            LoggerClass.dataloaderLogger.error("(:) Error in GetEntityByCriteria Method (:)");
            e.printStackTrace();
        }
        finally {
            closeSession(session);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public <T> T findEntityByCriteria(Class<T> entityClass,String primaryPropertyName1,String primaryPropertyName2, Serializable primaryId1,Serializable primaryId2) {

        LoggerClass.dataloaderLogger.info("getEntityByCriteria() : Entity Class ->> "+entityClass+" PrimaryPropertyNames ->> "+primaryPropertyName1+" & "+primaryPropertyName2+" Primary IDs ->>"+primaryId1+" & "+primaryId2);

        Session session = null;
        try {
            session = getSession();
            Object dataObject =  session.createCriteria(entityClass)
                    .add(Restrictions.eq(primaryPropertyName1, primaryId1))
                    .add(Restrictions.eq(primaryPropertyName2, primaryId2))
                    .uniqueResult();
            return (T) dataObject;
        }catch(Exception e) {
            LoggerClass.dataloaderLogger.error("(:) Error in GetEntityByCriteria Method two columns (:)");
            e.printStackTrace();
        }
        finally {
            closeSession(session);
        }
        return null;
    }



    @SuppressWarnings("unchecked")
    public <T> T findEntityByCriteria(Class<T> entityClass, String primaryPropertyName1, String primaryPropertyName2, String primaryPropertyName3, Serializable primaryId1, Serializable primaryId2, Serializable primaryId3) {

        LoggerClass.dataloaderLogger.info("getEntityByCriteria() : Entity Class ->> "+entityClass+" PrimaryPropertyNames ->> "+primaryPropertyName1+" & "+primaryPropertyName2+" & "+primaryPropertyName3+" Primary IDs ->>"+primaryId1+" & "+primaryId2+" & "+primaryId3);

        Session session = null;
        try {
            session = getSession();
            Object dataObject =  session.createCriteria(entityClass)
                    .add(Restrictions.eq(primaryPropertyName1, primaryId1))
                    .add(Restrictions.eq(primaryPropertyName2, primaryId2))
                    .add(Restrictions.eq(primaryPropertyName3, primaryId3))
                    .uniqueResult();
            return (T) dataObject;
        }catch(Exception e) {
            LoggerClass.dataloaderLogger.error("(:) Error in GetEntityByCriteria Method two columns (:)");
            e.printStackTrace();
        }
        finally {
            closeSession(session);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public <T> T findEntityByOrCriteria(Class<T> entityClass,String primaryPropertyName1,String primaryPropertyName2, Serializable primaryId1,Serializable primaryId2) {

        LoggerClass.dataloaderLogger.info("getEntityByCriteria() : Entity Class ->> "+entityClass+" PrimaryPropertyNames ->> "+primaryPropertyName1+" | "+primaryPropertyName2+" Primary IDs ->>"+primaryId1+" | "+primaryId2);

        Session session = null;
        try {
            session = getSession();
            Object dataObject =  session.createCriteria(entityClass)
                    .add(Restrictions.or(Restrictions.eq(primaryPropertyName1, primaryId1),
                            Restrictions.eq(primaryPropertyName2, primaryId2)))
                    .uniqueResult();
            return (T) dataObject;
        }catch(Exception e) {
            LoggerClass.dataloaderLogger.error("(:) Error in GetEntityByCriteria Method two columns (:)");
            e.printStackTrace();
        }
        finally {
            closeSession(session);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> loadEntitiesByCriteria(Class<T> entityClass) {

        LoggerClass.dataloaderLogger.info("loadEntitiesByCriteria() : Entity Class ->> "+entityClass);

        Session session = null;
        try {
            session = getSession();
            return (List<T>) session.createCriteria(entityClass).list();
        }catch(Exception e) {
            LoggerClass.dataloaderLogger.error("(:) Error in LoadEntitiesByCriteria Method (:)");
            e.printStackTrace();
        }
        finally {
            closeSession(session);
        }
        return null;
    }


    @SuppressWarnings("unchecked")
    public <T> List<T> loadEntitiesByCriteria(Class<T> entityClass,String primaryPropertyName1,String primaryPropertyName2, Serializable primaryId1,Serializable primaryId2) {

        LoggerClass.dataloaderLogger.info("loadEntitiesByCriteria() : Entity Class ->> "+entityClass+" PrimaryPropertyNames ->> "+primaryPropertyName1+" & "+primaryPropertyName2+" Primary IDs ->>"+primaryId1+" & "+primaryId2);

        Session session = null;
        try {
            session = getSession();
            return (List<T>) session.createCriteria(entityClass)
                    .add(Restrictions.eq(primaryPropertyName1, primaryId1))
                    .add(Restrictions.eq(primaryPropertyName2, primaryId2))
                    .list();
        }catch(Exception e) {
            LoggerClass.dataloaderLogger.error("(:) Error in loadEntitiesByCriteria Method two columns (:)");
            e.printStackTrace();
        }
        finally {
            closeSession(session);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> loadEntitiesByCriteria(Class<T> entityClass,String primaryPropertyName1,String primaryPropertyName2, String primaryPropertyName3, Serializable primaryId1,Serializable primaryId2, String primaryId3) {

        LoggerClass.dataloaderLogger.info("loadEntitiesByCriteria() : Entity Class ->> "+entityClass+" PrimaryPropertyNames ->> "+primaryPropertyName1+" & "+primaryPropertyName2+" & "+primaryPropertyName3+", Primary IDs ->>"+primaryId1+" & "+primaryId2+" & "+primaryId3);

        Session session = null;
        try {
            session = getSession();
            return (List<T>) session.createCriteria(entityClass)
                    .add(Restrictions.eq(primaryPropertyName1, primaryId1))
                    .add(Restrictions.eq(primaryPropertyName2, primaryId2))
                    .add(Restrictions.eq(primaryPropertyName3, primaryId3))
                    .list();
        }catch(Exception e) {
            LoggerClass.dataloaderLogger.error("(:) Error in loadEntitiesByCriteria Method two columns (:)");
            e.printStackTrace();
        }
        finally {
            closeSession(session);
        }
        return null;
    }


    @SuppressWarnings("unchecked")
    public <T> List<T> loadEntitiesByCriteria(Class<T> entityClass, List<ProjectionsDTO> projectionsList, List<RestrictionsDTO> restrictionsList) {

        LoggerClass.dataloaderLogger.info("loadEntitiesByCriteria() : Entity Class ->> "+entityClass+" ProjectionsList ->> "+projectionsList);

        Session session = null;
        try {
            session = getSession();

            ProjectionList projectionList = Projections.projectionList();
            projectionsList.forEach( (projectionsdto) -> {
                projectionList.add(Projections.property(projectionsdto.getPropertyName()),projectionsdto.getPropertyName());
            });

            Criteria criteria =  session.createCriteria(entityClass);
            criteria.setProjection(projectionList);

            restrictionsList.forEach( (restrictionsdto) -> {
                RestrictionsConditions condition = restrictionsdto.getConditions();
                switch (condition) {

                    case EQ :
                        Criterion eqCriterion = Restrictions.eq(restrictionsdto.getPropertyName(), restrictionsdto.getPropertyValue());
                        criteria.add(eqCriterion);
                        break;

                    case GT :
                        Criterion gtCriterion = Restrictions.gt(restrictionsdto.getPropertyName(), restrictionsdto.getPropertyValue());
                        criteria.add(gtCriterion);
                        break;

                    case LT :
                        Criterion ltCriterion = Restrictions.gt(restrictionsdto.getPropertyName(), restrictionsdto.getPropertyValue());
                        criteria.add(ltCriterion);
                        break;

                    case ILIKE :
                        Criterion ilikeCriterion = Restrictions.ilike(restrictionsdto.getPropertyName(), restrictionsdto.getPropertyValue());
                        criteria.add(ilikeCriterion);
                        break;

                    default : break;
                }
            });
            criteria.setResultTransformer(Transformers.aliasToBean(entityClass));
            return (List<T>) criteria.list();

        }catch(Exception e) {
            LoggerClass.dataloaderLogger.error("(:) Error in loadEntitiesByCriteria Method two columns (:)");
            e.printStackTrace();
        }
        finally {
            closeSession(session);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> loadEntitiesByCriteria(Class<T> entityClass,String propertyName, Serializable serializableValue) {

        LoggerClass.dataloaderLogger.info("loadEntitiesByCriteria() : Entity Class ->> "+entityClass+" PropertyName ->> "+propertyName+" Serializable Value ->>"+serializableValue);

        Session session = null;
        try {
            session = getSession();
            return (List<T>) session.createCriteria(entityClass)
                    .add(Restrictions.eq(propertyName, serializableValue))
                    .list();
        }catch(Exception e) {
            LoggerClass.dataloaderLogger.error("(:) Error in loadEntitiesByCriteria Method (:)");
            e.printStackTrace();
        }
        finally {
            closeSession(session);
        }
        return null;
    }
    @SuppressWarnings("unchecked")
    public <T> List<T> loadEntitiesByHQL(String hqlQuery) {

        LoggerClass.dataloaderLogger.info("loadEntitiesByHQL() : HQL Query ->> "+hqlQuery);

        Session session = null;
        try {
            session = getSession();
            return (List<T>) session.createQuery(hqlQuery).list();
        }catch(Exception e) {
            LoggerClass.dataloaderLogger.error("(:) Error in LoadEntitiesByHQL Method (:)");
            e.printStackTrace();
        }
        finally {
            closeSession(session);
        }
        return null;
    }

    public <T> Serializable findValueByPropertyName(Class<T> entityClass,String propertyName,Serializable propertyValue,String propertyNameValueToReturn) {

        LoggerClass.dataloaderLogger.info("findValueByPropertyName() : Entity Class ->> "+entityClass+" <<- Property Name ->>"+propertyName+" <<- Property Value ->> "+propertyValue+" <<- PropertyNameValue To Return ->> "+propertyNameValueToReturn);

        Session session = null;
        try {
            session = getSession();
            Object returnedObject =  session.createCriteria(entityClass)
                    .setProjection(Projections.property(propertyNameValueToReturn))
                    .add(Restrictions.eq(propertyName, propertyValue))
                    .uniqueResult();

            LoggerClass.dataloaderLogger.info("Serializable Single Value ->> "+returnedObject);

            return (Serializable)returnedObject;

        }catch(Exception e) {
            LoggerClass.dataloaderLogger.error("(:) Error in findValueByPropertyName Method (:)");
            e.printStackTrace();
        }
        finally {
            closeSession(session);
        }
        return null;
    }

    public String updateEntityByHQL(String hqlQuery) {

        LoggerClass.dataloaderLogger.info("updateEntityByCriteria() : HQL Query ->> "+hqlQuery);

        Session session = null;
        try {
            session = getSession();
            Query query = session.createQuery(hqlQuery);
            query.executeUpdate();
            //return ReturnTypes.SUCCESS;
            return "SUCCESS"; //TODO : Need to uncomment above line
        }catch(Exception e) {
            LoggerClass.dataloaderLogger.error("(:) Error in UpdateEntityByHQL Method (:)");
            e.printStackTrace();
        }
        finally {
            closeSession(session);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> loadEntitiesByCriteria(Class<T> entityClass, ArrayList<String> projectionPropertyList, String propertyName, Serializable serializableValue) {

        LoggerClass.dataloaderLogger.info("loadEntitiesByCriteria() : Entity Class ->> "+entityClass+" PropertyName ->> "+propertyName+" Serializable Value ->>"+serializableValue+" ProjectionPropertyList Size ->> "+projectionPropertyList.size());

        Session session = null;
        try {
            session = getSession();
            ProjectionList projectionList = Projections.projectionList();
            for(String projectionPropertyName : projectionPropertyList) {
                projectionList.add(Projections.property(projectionPropertyName),projectionPropertyName);
            }
            return (List<T>) session.createCriteria(entityClass)
                    .setProjection(projectionList)
                    .add(Restrictions.eq(propertyName, serializableValue))
                    .setResultTransformer(Transformers.aliasToBean(entityClass))
                    .list();
        }catch(Exception e) {
            LoggerClass.dataloaderLogger.error("(:) Error in loadEntitiesByCriteria Method (:)");
            e.printStackTrace();
        }
        finally {
            closeSession(session);
        }
        return null;
    }


    @SuppressWarnings("unchecked")
    public <T> List<Map<String,Object>> loadEntitiesByCriteriaAsAliasToMap(Class<T> entityClass, ArrayList<String> projectionPropertyList, String propertyName, Serializable serializableValue) {

        LoggerClass.dataloaderLogger.info("loadEntitiesByCriteriaAsAliasToMap() : Entity Class ->> "+entityClass+" PropertyName ->> "+propertyName+" Serializable Value ->>"+serializableValue+" ProjectionPropertyList Size ->> "+projectionPropertyList.size());

        Session session = null;
        try {
            session = getSession();
            ProjectionList projectionList = Projections.projectionList();
            for(String projectionPropertyName : projectionPropertyList) {
                projectionList.add(Projections.property(projectionPropertyName),projectionPropertyName);
            }
            return (List<Map<String,Object>>) session.createCriteria(entityClass)
                    .setProjection(projectionList)
                    .add(Restrictions.eq(propertyName, serializableValue))
                    .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
                    .list();
        }catch(Exception e) {
            LoggerClass.dataloaderLogger.error("(:) Error in loadEntitiesByCriteria Method (:)");
            e.printStackTrace();
        }
        finally {
            closeSession(session);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> loadDetailsBySqlQuery(String sqlQuery) {
        Session session = null;
        try {
            session = getSession();
            SQLQuery query = session.createSQLQuery(sqlQuery);
            return (List<Object[]>) query.list();
        } catch (Exception e) {
            LoggerClass.dataloaderLogger.error("(:) Error in GetEntityByCriteria Method two columns (:)");
            e.printStackTrace();
        } finally {
            closeSession(session);
        }
        return null;
    }

//    @SuppressWarnings("unchecked")
    public Integer uploadDataBySqlQuery(String sqlQuery) {
        Session session = null;
        try {
            session = getSession();
            SQLQuery query = session.createSQLQuery(sqlQuery);
            return query.executeUpdate();
        } catch (Exception e) {
            LoggerClass.dataloaderLogger.error("(:) Error in GetEntityByCriteria Method two columns (:)");
            e.printStackTrace();
        } finally {
            closeSession(session);
        }
        return null;
    }

//    @SuppressWarnings("unchecked")
    public <T>Object getEntitiesCountByCriteria(Class<T> entityClass) {

        LoggerClass.dataloaderLogger.info("getEntitiesCountByCriteria() : Entity Class ->> "+entityClass);

        Session session = null;
        try {
            session = getSession();
            return session.createCriteria(entityClass).setProjection(Projections.rowCount()).uniqueResult();
        }catch(Exception e) {
            LoggerClass.dataloaderLogger.error("(:) Error in getEntitiesCountByCriteria Method (:)");
            e.printStackTrace();
        }
        finally {
            closeSession(session);
        }
        return null;
    }


}

