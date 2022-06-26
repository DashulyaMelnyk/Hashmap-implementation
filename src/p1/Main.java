package p1;
import java.io.*;



public class Main {
    public static void main(String[] args) {
        HashMap<String,Array<Posicion>> map = new HashMap<>();
        try {
            BufferedReader in = new BufferedReader(new FileReader("file.txt")); //Reading file
            String line;
            int r = 1, c; // the row and the column of the position
            String[] words;
            Array<Posicion> tempPos;
            while((line = in.readLine()) != null){
                line = line.toLowerCase().replaceAll("[-.?!)(,:]", ""); //Making all letters to the lower case and getting rid of extra characters
                words = line.split(" "); // Getting the array of the words from the line
                for (String word : words) {
                    if (map.contains(word) && r == (map.get(word).get(map.get(word).size()-1).getRow())) { //Checking if the word already was in the same line
                        tempPos = map.get(word);
                        int index = tempPos.size() - 1;
                        c = line.indexOf(word, tempPos.get(index).getColumn()) + 1; // Calculating the column of the word if its in map yet
                    } else c = line.indexOf(word) + 1; // Calculating a column if it isn't in the same row
                    map.put(word, getArray(map, word, new Posicion(r, c))); // Inserting the position into the hash table
                }
                r++; // Increasing the row
            }
            in.close();
            map.printMap(); // Printing the map
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // The method that generates an array
    // If the word is already in map, method gets it and adds a new position
    public static Array getArray(HashMap<String, Array<Posicion>> map, String key, Posicion pos){
        Array<Posicion> arrPos = new Array<>();
        if(map.contains(key)) {
            //System.out.println(key + "=" + map);
            arrPos = map.get(key);}
        arrPos.add(pos);
        return arrPos;
    }

}
