package core.check;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class StringCheck {
    public static void main(String[] args) {
        Set<String> ids = new HashSet<>();
        ids.addAll(Arrays.asList("33606e7e-319e-475d-bb0f-2695382fab98", "359a30cb-4bc4-4ba2-8265-f621017dc15c", "5f7f165b-5b51-437f-beab-e7622b56e53a",
                "7c96eb72-7100-4cab-bf81-aee14d70160d", "c29b762f-5ca6-4b5c-b814-2393fe095268", "67c2c7ba-bc40-4647-abba-b61a6fa93c8c",
                "718228b2-4c8b-47e5-9c91-d7703e338347", "dbcf44aa-36fd-4a11-b040-c65195c48c7c", "d518c91e-ac18-4934-ab86-219521fb4f3d"));
        System.out.println("ids = " + ids);

        Set<String> registeredIds = new HashSet<>();
        registeredIds.addAll(Arrays.asList(
                "33606e7e-319e-475d-bb0f-2695382fab98",
                "359a30cb-4bc4-4ba2-8265-f621017dc15c",
                "5f7f165b-5b51-437f-beab-e7622b56e53a",
                "67c2c7ba-bc40-4647-abba-b61a6fa93c8c",
                "718228b2-4c8b-47e5-9c91-d7703e338347",
                "7c96eb72-7100-4cab-bf81-aee14d70160d",
                "c29b762f-5ca6-4b5c-b814-2393fe095268",
                "d518c91e-ac18-4934-ab86-219521fb4f3d",
                "dbcf44aa-36fd-4a11-b040-c65195c48c7c"
        ));

        System.out.println("registeredIds = " + registeredIds);

        assert registeredIds.containsAll(ids);
    }

    static void batchIdCheck() {
        String batchId = "\"f7bf66c0-f1ba-4457-8381-7b6d244d91ba\"";
        System.out.println("batchId = " + batchId);
        batchId = batchId.replace("\"", "");
        System.out.println("batchId = " + batchId);
    }
}
