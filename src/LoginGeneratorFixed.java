import java.util.*;
import java.io.*;
import java.text.Normalizer;
import java.util.regex.Pattern;

public class LoginGeneratorFixed {

public LoginGeneratorFixed() {
        super();
}
public static String DefineUserID (String fullName, String userType) {

fullName = sanitizeString(fullName).toLowerCase();
String[] names = fullName.split(" ");
List<String> list = new ArrayList<String>(Arrays.asList(names));
List<String> namesToBeClean = new ArrayList<String>(Arrays.asList("do", "da", "de", "e", "dos", "das"));
String UserID = null;

try{

list.removeAll(namesToBeClean);
fullName = String.join(" ", list);
names = fullName.split(" ");
int namesAmount = names.length;

switch(namesAmount)
{
        case 0: return null;

        case 1:
        UserID = tryUserID(UserID, userType, names[0]);
        break;

        case 2:
        UserID = tryUserID(UserID, userType, names[0].charAt(0) + "." + names[1]);
        UserID = tryUserID(UserID, userType, names[0] + "." + names[1].charAt(0));
        UserID = tryUserID(UserID, userType, names[0] + "." + names[1]);
        for(int i = names[1].length()-1; i>=0; i--){
                UserID = tryUserID(UserID, userType, names[0] + "." + names[1].substring(0,i));
        }
        for(int i = names[0].length()-1; i>=0; i--){
                UserID = tryUserID(UserID, userType, names[0].substring(0,i) + "." + names[1]);
        }
        break;

        default:
        for(int j = 1;j<names.length-2;j++){
        for(int i = 1;i<names.length-1;i++){
                if(names[j] != names[names.length - i]){
                UserID = tryUserID(UserID, userType, names[0].charAt(0) + "." + names[names.length - i]);
                UserID = tryUserID(UserID, userType, names[0].charAt(0) + "." + names[j]);
                UserID = tryUserID(UserID, userType, names[0].charAt(0) + "." + names[j].charAt(0) + names[names.length - i]);
                UserID = tryUserID(UserID, userType, names[0].charAt(0) + "." + names[j] + names[names.length - i].charAt(0));
                UserID = tryUserID(UserID, userType, names[0].charAt(0) + "." + names[j] + names[names.length - i]);
                UserID = tryUserID(UserID, userType, names[0].charAt(0) + "" + names[j].charAt(0) + "." + names[names.length - i]);
                UserID = tryUserID(UserID, userType, names[0].charAt(0) + names[j] + "." + names[names.length - i].charAt(0));
                UserID = tryUserID(UserID, userType, names[0].charAt(0) + names[j] + "." + names[names.length - i]);
                UserID = tryUserID(UserID, userType, names[0] + "." + names[names.length - i].charAt(0));
                UserID = tryUserID(UserID, userType, names[0] + "." + names[names.length - i]);
                UserID = tryUserID(UserID, userType, names[0] + "." + names[j].charAt(0));
                UserID = tryUserID(UserID, userType, names[0] + "." + names[j].charAt(0) + names[names.length - i].charAt(0));
                UserID = tryUserID(UserID, userType, names[0] + "." + names[j].charAt(0) + names[names.length - i]);
                UserID = tryUserID(UserID, userType, names[0] + "." + names[j]);
                UserID = tryUserID(UserID, userType, names[0] + "." + names[j] + names[names.length - i].charAt(0));
                UserID = tryUserID(UserID, userType, names[0] + names[j].charAt(0) + "." + names[names.length - i].charAt(0));
                UserID = tryUserID(UserID, userType, names[0] + names[j].charAt(0) + "." + names[names.length - i]);
                UserID = tryUserID(UserID, userType, names[0] + names[j] + "." + names[names.length - i].charAt(0));
                UserID = tryUserID(UserID, userType, names[0] + "." + names[j] + names[names.length - i]);
                UserID = tryUserID(UserID, userType, names[0] + names[j] + "." + names[names.length - i]);
        }}}
}
if (userType != "Terceiro"){
        try{UserID = UserID.replace(".", "");} catch(Exception e) {}
        return UserID;
}
}
catch(Exception e) {
        e.printStackTrace();
        return UserID;
}
finally {
}
return UserID;
}

public static String tryUserID (String UserID, String userType, String nextTry){
        if(!userType.equals("Terceiro"))
        {
        nextTry = nextTry.replace(".", "");
        }
        // System.out.println("Tentando o login: " + nextTry);
        if(UserID == null && !isBlacklisted(nextTry) && !userLoginExists(nextTry))
        {
                try{
                        nextTry = nextTry.substring(0, 20);
                }catch(Exception e){}
                return nextTry;
        }
        return UserID;
}

private static boolean userLoginExists(String UserID)
{
        String filePath = "D:/Projetos/LoginGen/LoginGenerator/src/TestLoginDB.txt";
        boolean found = false;
try{
        File file = new File(filePath);
        if (!file.exists()) {
                file.createNewFile();
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        List<String> lines = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
                if (line.contains(UserID)) {
                found = true;
                }
                lines.add(line);
        }
        reader.close();
        if (!found) {
                lines.add(UserID);
                FileWriter writer = new FileWriter(file);
                for (String str : lines) {
                writer.write(str + System.lineSeparator());
                }
                writer.close();
        }
        }
        catch (IOException e) {e.printStackTrace();}
        return found;
}
private static boolean isBlacklisted(String UserID){
        boolean blacklisted = false;
        String filePath = "D:/Projetos/LoginGen/LoginGenerator/src/LoginBlacklist.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {
        String line;
        while ((line = br.readLine()) != null) {
                if (UserID.contains(line)) {
                        blacklisted = true;
                        System.out.println("Login proibido, patrão: " + UserID);
                        break;
                }
        }
        
        } catch (IOException e) {
                e.printStackTrace();
        }
        return blacklisted;
}
public static String sanitizeString(String input) {

input = Normalizer.normalize(input, Normalizer.Form.NFD);
input = input.replace("'", "");
Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
String cleanString = pattern.matcher(input).replaceAll("");
return cleanString;
}
}