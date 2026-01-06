import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Search_Processing {

    public static void main(String[] args) {

        SearchInterface result = Optional.ofNullable(Inventory.findItem("A100"))
                    .map(inv -> (SearchInterface) inv)
                    .orElseGet(ItemPlaceholder::new);

        System.out.println(result.getInfo());

        if (result instanceof Inventory inventory) {

            Set<String> uniqueItemIds =
                inventory.getPalletItemIds()
                         .stream()
                         .flatMap(pallet -> pallet.stream())
                         .collect(Collectors.toSet());

            System.out.println("Unique Item IDs: " + uniqueItemIds);
        }
    }
}
