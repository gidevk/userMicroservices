package DOA;

import Entity.WinRecord;

public interface WinRecordDAO {
    WinRecord saveWinRecord(WinRecord winRecord);

    WinRecord getWinRecordById(int Id);
}
