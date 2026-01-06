// File: ItemPlaceholder.java

class ItemPlaceholder implements SearchInterface{
    // This is a highly resource-intensive object to create.
    public ItemPlaceholder() { 
        System.out.println("ALERT: Creating expensive placeholder object!"); 
    }
    
    @Override
    public String getInfo() { return "ID-NOT-FOUND: Placeholder Item"; }
}
