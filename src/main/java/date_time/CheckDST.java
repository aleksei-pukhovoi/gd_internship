package date_time;

import java.time.ZonedDateTime;
import java.time.zone.ZoneRules;
import java.time.zone.ZoneRulesProvider;


public class CheckDST {

    public static boolean isDST(String dateString) {
        ZonedDateTime dateTime = ZonedDateTime.parse(dateString);
        ZoneRules zoneRules = ZoneRulesProvider.getRules(dateTime.getZone().getId(), false);
        return zoneRules.isDaylightSavings(dateTime.toInstant());
    }

    public static void main(String[] args) {
        String startDSTinLondon = "2021-03-28T01:00:00Z";
        String startDSTinNY = "2021-03-14T07:00:00Z";
        String zoneLondon = "[Europe/London]";
        String zoneMoscow = "[Europe/Moscow]";
        String zoneNY = "[America/New_York]";

        System.out.printf("%s is a Daylight Saving Time:%n", startDSTinLondon);
        System.out.printf("- in London - %b%n",isDST(startDSTinLondon+ zoneLondon));
        System.out.printf("- in Moscow - %b%n",isDST(startDSTinLondon+zoneMoscow));
        System.out.printf("- in New-York - %b%n",isDST(startDSTinLondon+zoneNY));
        System.out.println("++++++++++++++++++++++++++");
        System.out.printf("%s is a Daylight Saving Time:%n", startDSTinNY);
        System.out.printf("- in London - %b%n",isDST(startDSTinNY+ zoneLondon));
        System.out.printf("- in Moscow - %b%n",isDST(startDSTinNY+zoneMoscow));
        System.out.printf("- in New-York - %b%n",isDST(startDSTinNY+zoneNY));
    }
}
