package utilities;

import lombok.SneakyThrows;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Random;

import static utilities.TestConstants.ShopConstants.TAX;

public class ItemFactory {
    private static RealItem realItem;
    private static VirtualItem virtualItem;

    public static RealItem createNewRealItem() {
        realItem = new RealItem();
        realItem.setName(GenerateTestData.generateRandomString());
        realItem.setPrice(GenerateTestData.generateRandomDouble());
        realItem.setWeight(GenerateTestData.generateRandomDouble());
        return realItem;
    }

    public static RealItem createNewRealItem(String name, double price, double weight) {
        realItem = new RealItem();
        realItem.setName(name);
        realItem.setPrice(price);
        realItem.setWeight(weight);
        return realItem;
    }

    public static VirtualItem createNewVirtualItem() {
        virtualItem = new VirtualItem();
        virtualItem.setName(GenerateTestData.generateRandomString());
        virtualItem.setPrice(GenerateTestData.generateRandomDouble());
        virtualItem.setSizeOnDisk(GenerateTestData.generateRandomDouble());
        return virtualItem;
    }

    public static VirtualItem createNewVirtualItem(String name, double price, double sizeOnDisk) {
        virtualItem = new VirtualItem();
        virtualItem.setName(name);
        virtualItem.setPrice(price);
        virtualItem.setSizeOnDisk(sizeOnDisk);
        return virtualItem;
    }

    @SneakyThrows
    public static List<RealItem> getListOfRealItems(Cart cart) {
        Field realItemsField = Cart.class.getDeclaredField("realItems");
        realItemsField.setAccessible(true);
        return (List<RealItem>) realItemsField.get(cart);
    }

    @SneakyThrows
    public static List<VirtualItem> getListOfVirtualItems(Cart cart) {
        Field virtualItemsField = Cart.class.getDeclaredField("virtualItems");
        virtualItemsField.setAccessible(true);
        return (List<VirtualItem>) virtualItemsField.get(cart);
    }

    public static RealItem getRealItemToDelete(Cart cart) {
        return getListOfRealItems(cart).stream()
                .skip(new Random().nextInt(getListOfRealItems(cart).size()))
                .findFirst()
                .orElse(null);
    }

    public static VirtualItem getVirtualItemToDelete(Cart cart) {
        return getListOfVirtualItems(cart).stream()
                .skip(new Random().nextInt(getListOfVirtualItems(cart).size()))
                .findFirst()
                .orElse(null);
    }

    public static double calculateTotalPrice(Cart cart) {
        double total = 0;
        List<RealItem> listOfRealItems = getListOfRealItems(cart);
        if (listOfRealItems.size() > 0) {
            for (RealItem item : listOfRealItems) {
                total += (1 + TAX) * item.getPrice();
            }
        }
        List<VirtualItem> listOfVirtualItems = getListOfVirtualItems(cart);
        if (listOfVirtualItems.size() > 0) {
            for (VirtualItem item : listOfVirtualItems) {
                total += (1 + TAX) * item.getPrice();
            }
        }
        return total;
    }

    public static boolean isRealItemListsEqual(List<RealItem> listA, List<RealItem> listB) {
        return listA.stream()
                .anyMatch(a -> listB.stream()
                        .anyMatch(b -> a.getName().equals(b.getName())
                                && a.getPrice() == b.getPrice()
                                && a.getWeight() == b.getWeight()));
    }

    public static boolean isVirtualItemListsEqual(List<VirtualItem> listA, List<VirtualItem> listB) {
        return listA.stream()
                .anyMatch(a -> listB.stream()
                        .anyMatch(b -> a.getName().equals(b.getName())
                                && a.getPrice() == b.getPrice()
                                && a.getSizeOnDisk() == b.getSizeOnDisk()));
    }

}
