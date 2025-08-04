package org.example;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        //Get Order Data from User
        System.out.println("Welcome to the Interactive Order Processor!");
        System.out.println();
        System.out.println("--- Enter Order Details ---");
        System.out.print("Enter unit price: ");
        double unitPrice = sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = sc.nextInt();
        sc.nextLine();
        System.out.print("Is customer member (true/false)?: ");
        String isCustomer = sc.next().toLowerCase();
        System.out.print("Enter customer tier (Regular, Silver, Gold): ");
        String customerTier = sc.next().toLowerCase();
        System.out.print("Enter shipping zone (ZoneA, ZoneB, ZoneC, Unknown): ");
        String shippingZone = sc.next();
        System.out.print("Enter discount code (SAVE10, FREESHIP, or \"\" for none): ");
        String discountCode = sc.next();
        System.out.println();

        System.out.println("--- Order Details ---");
        System.out.printf("Unit Price: %.2f\n",unitPrice);
        System.out.println("Quantity: " + quantity);
        System.out.println("Is Member: " + isCustomer);
        System.out.println("Customer Tier: " + customerTier);
        System.out.println("Shipping Zone: " + shippingZone);
        System.out.println("Discount Code: " + discountCode);
        System.out.println();

        System.out.println("--- Calculation Steps ---");

        double subTotal = unitPrice * quantity;
        System.out.printf("Initial Subtotal: $%.2f\n",subTotal);

        double discountAmount;
        double totalCustomerAmount;
        if (customerTier.equals("gold")){
            discountAmount = subTotal * 0.15;
            totalCustomerAmount = subTotal - discountAmount;
            System.out.printf("After Tier Discount (Gold - 15%%): $%.2f\n",totalCustomerAmount);
        } else if (customerTier.equals("silver")) {
            discountAmount = subTotal * 0.10;
            totalCustomerAmount = subTotal - discountAmount;
            System.out.printf("After Tier Discount (Silver - 10%%): $%.2f\n",totalCustomerAmount);
        }else {
            totalCustomerAmount = subTotal - 0;
            System.out.printf("No Tier Discount Applies: $%.2f\n",totalCustomerAmount);
        }

        double quantityDiscount;
        double totalQuantityAmount;
        double shippingCost;
        if (quantity>= 5){
            quantityDiscount = totalCustomerAmount * .05;
            totalQuantityAmount = totalCustomerAmount - quantityDiscount;
            System.out.printf("After Quantity Discount (5%% for >=5 items): $%.2f\n", totalQuantityAmount);
        }else {
            totalQuantityAmount = totalCustomerAmount - 0;
            System.out.printf("No Quantity Discount Applied: $%.2f\n", totalQuantityAmount);
        }

        double totalcodeDiscount = totalQuantityAmount;
        if (discountCode.equals("SAVE10") && totalQuantityAmount > 75){
            totalcodeDiscount = totalQuantityAmount - 10;
            System.out.printf("After Promotional Code (SAVE10 for >$75): $%.2f\n",totalcodeDiscount);
        } else if (discountCode.equals("FREESHIP")) {
            shippingCost = 0.00;
            System.out.printf("After Promotional Code (FREESHIP): Your Shipping is now $%.2f\n",shippingCost);
        } else {
            System.out.printf("No Promotional Discount Applied: $%.2f\n",totalcodeDiscount);
        }
        double totalSurcharge;
        if (totalcodeDiscount < 25){
            double withSurcharge = 3;
            totalSurcharge = totalcodeDiscount - withSurcharge;
            System.out.printf("After Small Order Surcharge (if applicable): $%.2f (with surcharge)\n", totalSurcharge);
        }else{
            totalSurcharge = totalcodeDiscount;
            System.out.printf("After Small Order Surcharge (if applicable): $%.2f (No surcharge)\n", totalSurcharge);
        }

        System.out.println();

        if (discountCode.equals("FREESHIP")){
            shippingCost = 0;
            System.out.printf("Shipping Cost: $%.2f (Freeship)\n", shippingCost);
        }else {
            switch (shippingZone){
                case "ZoneA":shippingCost = 5;
                    System.out.printf("Shipping Cost: $%.2f (ZoneA)\n", shippingCost);
                break;
                case "ZoneB":shippingCost = 12.50;
                    System.out.printf("Shipping Cost: $%.2f (ZoneB)\n", shippingCost);
                break;
                case "ZoneC":shippingCost = 20;
                    System.out.printf("Shipping Cost: $%.2f (ZoneC)\n", shippingCost);
                break;
                default: shippingCost = 25;
                    System.out.printf("Shipping Cost: $%.2f (Unknown)\n", shippingCost);

            }
        }
        System.out.println();
        double finalOrderTotal = totalSurcharge + shippingCost;
        System.out.printf("Final Order Total: $%.2f",finalOrderTotal);

        System.out.println("\n--- Interactive String Equality ---");

        sc.nextLine();
        System.out.print("First string for comparison: ");
        String str1 = sc.nextLine();
        System.out.print("Second string for comparison: ");
        String str2 = sc.nextLine();

        System.out.println();
        System.out.println("String 1: " + str1);
        System.out.println("String 2: " + str2);

        System.out.println();
        if (str1 == str2){
            System.out.println("String 1 == String 2: " + (str1 == str2) + " (Same object reference in memory)");
        }else {
            System.out.println("String 1 == String 2: " + (str1 == str2) +"(Compares references, which are different for user input strings)");
        }
        if (str1.equals(str2)){
            System.out.println("String 1 .equals() String 2: " + (str1.equals(str2)) + " (Content is exactly the same)");
        }else {
            System.out.println("String 1 .equals() String 2: " + (str1.equals(str2)) +"(Content is different due to case)");
        }
        if (str1.equalsIgnoreCase(str2)){
            System.out.println("String 1 .equalsIgnoreCase() String 2: " + (str1.equalsIgnoreCase(str2)) + "(Content is identical, ignoring case)");
        }else {
            System.out.println("String 1 .equalsIgnoreCase() String 2: " + (str1.equalsIgnoreCase(str2)) + " (Content is different even ignoring case)");
        }

    }
}