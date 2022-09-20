package zw.co.chivasa.henry.youtapcodingtest;

import zw.co.chivasa.henry.youtapcodingtest.dto.ContactDTO;

public abstract class AbstractTestData {
    protected final Integer validUserId = 7;
    protected final String validUserName = "Elwyn.Skiles";
    protected final String validEmail = "Telly.Hoeger@billy.biz";
    protected final String validPhone = "210.067.6132";

    protected final Integer invalidUserId = 999;
    protected final String invalidUserName = "DangerMouse";
    protected final String invalidEmail = "mickey@mouse.com";
    protected final String invalidPhone = "123.456.7890";

    protected final ContactDTO defaultContactDTO = new ContactDTO(-1, null, null);
    protected final ContactDTO validContactDTO = new ContactDTO(validUserId, validEmail, validPhone);
}
