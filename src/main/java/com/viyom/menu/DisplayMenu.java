package com.viyom.menu;

public class DisplayMenu {
    public void displayMenu(){
        StringBuilder menu=new StringBuilder();
        menu.append("//Add product by product no").append("\n")
                .append("soup. soup tin").append("\n")
                .append("bread. loaf of bread").append("\n")
                .append("milk. bottle of milk").append("\n")
                .append("apples. apples each").append("\n");
        System.out.println(menu.toString());
    }

    public void promptForProductNo() {
        StringBuilder prompt = new StringBuilder();
        prompt.append("Enter product name to add");
        System.out.println(prompt.toString());
    }

    public void promptForQuantity() {
        StringBuilder prompt = new StringBuilder();
        prompt.append("Enter quantity");
        System.out.println(prompt.toString());
    }

    public void promptAddMoreProducts() {
        StringBuilder prompt = new StringBuilder();
        prompt.append("Enter 1 to add more Product or 0");
        System.out.println(prompt.toString());
    }
}
