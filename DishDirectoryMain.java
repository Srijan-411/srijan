
 
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
 
class DishDirectory {
 
    private final ArrayList<String> records = new ArrayList<>();
 
    public void addEntry(String entry) {
        if (entry == null) return;
 
        String trimmed = entry.trim();
        if (trimmed.isEmpty()) return;
 
        int colonIndex = trimmed.indexOf(':');
        if (colonIndex <= 0 || colonIndex == trimmed.length() - 1) {
            
            return;
        }
 
        String namePart = trimmed.substring(0, colonIndex).trim();
        String ratingPart = trimmed.substring(colonIndex + 1).trim();
 
        if (namePart.isEmpty() || ratingPart.isEmpty()) {
       
            return;
        }
 
   
        try {
            Double.parseDouble(ratingPart);
        } catch (NumberFormatException e) {
           
            return;
        }
 
        
        records.add(namePart + ":" + ratingPart);
    }
 
   
    public List<String> findByRatingThreshold(double threshold) {
        List<String> result = new ArrayList<>();
 
        for (String entry : records) {
            if (entry == null) continue;
 
            int colonIndex = entry.indexOf(':');
            if (colonIndex <= 0 || colonIndex == entry.length() - 1) {
               
                continue;
            }
 
            String namePart = entry.substring(0, colonIndex).trim();
            String ratingPart = entry.substring(colonIndex + 1).trim();
 
            if (namePart.isEmpty() || ratingPart.isEmpty()) {
                continue;
            }
 
            double rating;
            try {
                rating = Double.parseDouble(ratingPart);
            } catch (NumberFormatException e) {
           
                continue;
            }
 
            if (rating >= threshold) {
                result.add(namePart);
            }
        }
 
        return result;
    }
}
 
public class DishDirectoryMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DishDirectory directory = new DishDirectory();
 
        System.out.println("Enter the number of recipes to be added");
        int n = 0;
        try {
            String line = sc.nextLine();
            if (line != null) {
                n = Integer.parseInt(line.trim());
            }
        } catch (NumberFormatException e) {
           
            n = 0;
        }
 
        System.out.println("Enter recipe details (name : rating)");
        for (int i = 0; i < n; i++) {
            String entry = sc.nextLine();
            directory.addEntry(entry);
        }
 
        System.out.println("Enter the minimum rating");
        double threshold = 0.0;
        try {
            String t = sc.nextLine();
            if (t != null) {
                threshold = Double.parseDouble(t.trim());
            }
        } catch (NumberFormatException e) {
           
        }
 
        List<String> filtered = directory.findByRatingThreshold(threshold);
 
        System.out.println("Recipes with a rating of at least " + threshold + ":");
        if (!filtered.isEmpty()) {
            for (String name : filtered) {
                System.out.println(name);
            }
        }
      
 
        sc.close();
    }
}