package date_time;

import java.time.Instant;
import java.time.ZoneId;
import java.time.zone.ZoneRules;
import java.time.zone.ZoneRulesProvider;


public class CheckDST {

    public static boolean isDST(String dateString, ZoneId zoneId) {
        Instant instant = Instant.parse(dateString);
        ZoneRules zoneRules = ZoneRulesProvider.getRules(zoneId.getId(), false);
        return zoneRules.isDaylightSavings(instant);
    }

    public static void main(String[] args) {
        String startDSTinEU = "2021-03-28T01:00:00Z";
        String startDSTinUSA = "2021-03-14T07:00:00Z";
        ZoneId zone1 = ZoneId.of("Europe/London");
        ZoneId zone2 = ZoneId.of("Europe/Moscow");
        ZoneId zone3 = ZoneId.of("America/New_York");
        System.out.printf("%s is a Daylight Saving Time:%n", startDSTinEU);
        System.out.printf("- in London - %b%n", isDST(startDSTinEU,zone1));
        System.out.printf("- in Moscow - %b%n", isDST(startDSTinEU,zone2));
        System.out.printf("- in New-York - %b%n", isDST(startDSTinEU,zone3));
        System.out.println("+++++++++++++++++++++++++");
        System.out.printf("%s is a Daylight Saving Time:%n", startDSTinUSA);
        System.out.printf("- in London - %b%n", isDST(startDSTinUSA,zone1));
        System.out.printf("- in Moscow - %b%n", isDST(startDSTinUSA,zone2));
        System.out.printf("- in New-York - %b%n", isDST(startDSTinUSA,zone3));
    }
}
