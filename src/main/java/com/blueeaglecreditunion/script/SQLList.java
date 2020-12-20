package com.blueeaglecreditunion.script;

public class SQLList {

    public SQLList() {

    }


    public static String getMemberCardData() {
        return "WITH MEMBER_EMAIL AS\n" +
                "(\n" +
                " SELECT\n" +
                "     PERSON_SERIAL,\n" +
                "     PERSON_EMAIL\n" +
                " FROM\n" +
                "     (\n" +
                "         SELECT\n" +
                "             PERSON.SERIAL AS PERSON_SERIAL,\n" +
                "             PERSON_CONTACT.VALUE AS PERSON_EMAIL,\n" +
                "             ROW_NUMBER() OVER(PARTITION BY PERSON.SERIAL\n" +
                "                 ORDER BY PERSON_CONTACT.ORDINAL) AS MIN_ORDINAL\n" +
                "\n" +
                "         FROM\n" +
                "             CORE.PERSON AS PERSON\n" +
                "                 INNER JOIN CORE.PERSON_CONTACT AS PERSON_CONTACT\n" +
                "                            ON PERSON.SERIAL = PERSON_CONTACT.PARENT_SERIAL\n" +
                "         WHERE\n" +
                "             (PERSON_CONTACT.CATEGORY = 'PE' OR PERSON_CONTACT.CATEGORY = 'BE')\n" +
                "           AND PERSON_CONTACT.VALUE IS NOT NULL\n" +
                "           AND PERSON_CONTACT.BAD_CONTACT = 'N'\n" +
                "           AND PERSON_CONTACT.EXPIRATION_DATE IS NULL\n" +
                "\n" +
                "     )\n" +
                " WHERE\n" +
                "         MIN_ORDINAL = 1\n" +
                ")\n" +
                "\n" +
                "SELECT\n" +
                "    CARD_NUMBER,\n" +
                "    MEMBER_EMAIL.PERSON_EMAIL,\n" +
                "    PERSON.LAST_NAME || COALESCE((', ') || PERSON.FIRST_NAME, '') AS MEMBER_NAME,\n" +
                "    ACCOUNT_NUMBER\n" +
                "FROM\n" +
                "    CORE.CARD AS CARD\n" +
                "\n" +
                "    INNER JOIN\n" +
                "        CORE.CARD_PLASTIC AS CARD_PLASTIC\n" +
                "        ON CARD.SERIAL = CARD_PLASTIC.PARENT_SERIAL\n" +
                "    INNER JOIN\n" +
                "        CORE.PERSON AS PERSON\n" +
                "        ON CARD_PLASTIC.PERSON_SERIAL = PERSON.SERIAL\n" +
                "    INNER JOIN\n" +
                "        CORE.ACCOUNT AS ACCOUNT\n" +
                "        ON CARD.PRIMARY_ACCOUNT_SERIAL = ACCOUNT.SERIAL\n" +
                "    LEFT OUTER JOIN\n" +
                "        MEMBER_EMAIL ON MEMBER_EMAIL.PERSON_SERIAL = PERSON.SERIAL\n" +
                "ORDER BY\n" +
                "    CARD_NUMBER";
    }

}
