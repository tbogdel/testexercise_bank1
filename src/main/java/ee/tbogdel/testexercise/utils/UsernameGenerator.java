package ee.tbogdel.testexercise.utils;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class UsernameGenerator {

        private static final String LOWERCASE_CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
        private static final String UPPERCASE_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        private static final String NUMERIC_CHARACTERS = "0123456789";

        private static final Random RANDOM = new Random();


        public static String generateUsername(int length) {
            List<String> characterGroups = new ArrayList<>();
            Collections.addAll(characterGroups, LOWERCASE_CHARACTERS, UPPERCASE_CHARACTERS, NUMERIC_CHARACTERS);

            StringBuilder sb = new StringBuilder(length);
            String randomCharacterGroup;
            char randomChar;

            for (int i = 0; i < length; i++) {
                randomCharacterGroup = characterGroups.get(RANDOM.nextInt(characterGroups.size()));
                randomChar = randomCharacterGroup.charAt(RANDOM.nextInt(randomCharacterGroup.length()));
                sb.append(randomChar);
            }
            return sb.toString();
        }
}
