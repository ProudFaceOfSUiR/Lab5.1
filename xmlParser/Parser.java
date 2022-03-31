package xmlParser;

import Commands.Save;
import Exceptions.NotSatisyingParametrException;
import com.company.Database;
import com.company.Person;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class Parser {
    public static void parse(Database database, String file_path){
        BufferedInputStream bis = null;
        FileInputStream  fis= null;

        try
        {
            fis = new FileInputStream(file_path);
            bis = new BufferedInputStream(fis);
            BufferedReader r = new BufferedReader(
                    new InputStreamReader(bis, StandardCharsets.UTF_8));
            String newLine;
            newLine = r.readLine();
            while(!(newLine.toLowerCase(Locale.ROOT).trim().equals("<collection>"))){
                newLine = r.readLine();
            }
            while(!(newLine.toLowerCase(Locale.ROOT).trim().equals("</collection>"))){
                if (newLine.toLowerCase(Locale.ROOT).trim().equals("<person>")){
                    newLine = r.readLine();
                    if(!newLine.contains("<Name>")||!newLine.contains("</Name>")) throw new NullPointerException();
                    String name = newLine.trim().replace("<Name>", "").replace("</Name>","");
                    newLine = r.readLine().trim();
                    if(!newLine.equals("<Coordinates>")) throw new NullPointerException();
                    newLine = r.readLine();
                    if(!newLine.contains("<x>")||!newLine.contains("</x>")) throw new NullPointerException();
                    Integer coordX = Integer.parseInt(newLine.trim().replace("<x>","").replace("</x>",""));
                    if (coordX<-616) throw new NotSatisyingParametrException("x should be more than -616");
                    newLine = r.readLine();
                    float coordY = 0;
                    if(!newLine.contains("<y>")||!newLine.contains("</y>")) throw new NullPointerException();
                    newLine = newLine.trim().replace("<y>","").replace("</y>","");
                    if (!newLine.equals(""))
                        coordY = Float.parseFloat(newLine);
                    if(!r.readLine().trim().equals("</Coordinates>")) throw new NullPointerException();
                    newLine = r.readLine();
                    if(!newLine.contains("<creationDate>")||!newLine.contains("</creationDate>")) throw new NullPointerException();
                    String creationDate = newLine.trim().replace("<creationDate>","").replace("</creationDate>","");
                    newLine = r.readLine();
                    if(!newLine.contains("<Height>")||!newLine.contains("</Height>")) throw new NullPointerException();
                    Integer height = Integer.parseInt(newLine.trim().replace("<Height>","").replace("</Height>",""));
                    if (height<0) throw new NotSatisyingParametrException("height should be more than 0");
                    newLine = r.readLine();
                    if(!newLine.contains("<eyeColor>")||!newLine.contains("</eyeColor>")) throw new NullPointerException();
                    Person.Color eyeColor = Person.Color.valueOf(newLine.trim().replace("<eyeColor>","").replace("</eyeColor>","").toUpperCase(Locale.ROOT));
                    newLine = r.readLine();
                    if(!newLine.contains("<hairColor>")||!newLine.contains("</hairColor>")) throw new NullPointerException();
                    Person.Color hairColor = Person.Color.valueOf(newLine.trim().replace("<hairColor>","").replace("</hairColor>","").toUpperCase(Locale.ROOT));
                    newLine = r.readLine();
                    if(!newLine.contains("<nationality>")||!newLine.contains("</nationality>")) throw new NullPointerException();
                    Person.Country nationality = Person.Country.valueOf(newLine.trim().replace("<nationality>","").replace("</nationality>","").toUpperCase(Locale.ROOT));

                    if(!r.readLine().trim().equals("<Location>")) throw new NullPointerException();
                    newLine = r.readLine();

                    if(!newLine.contains("<x>")||!newLine.contains("</x>")) throw new NullPointerException();
                    Long locX = Long.parseLong(newLine.trim().replace("<x>","").replace("</x>",""));

                    newLine = r.readLine();
                    if(!newLine.contains("<y>")||!newLine.contains("</y>")) throw new NullPointerException();
                    newLine = newLine.trim().replace("<y>","").replace("</y>","");
                    float locY = 0;
                    if(!newLine.equals(""))
                        locY = Float.parseFloat(newLine);
                    newLine = r.readLine();
                    if(!newLine.contains("<z>")||!newLine.contains("</z>")) throw new NullPointerException();
                    Float locZ = Float.valueOf(newLine.trim().replace("<z>","").replace("</z>",""));
                    database.addNewElement(new Person(database.generateID() ,name,coordX,coordY,null,height,eyeColor,hairColor,nationality,locX, locY,locZ));
                    if(!r.readLine().trim().equals("</Location>")) throw new NullPointerException();
                    if(!r.readLine().trim().equals("</Person>")) throw new NullPointerException();
                }
                newLine = r.readLine();
            }

        }catch(FileNotFoundException fnfe)
        {
            System.out.println("The specified file not found" + fnfe);
        }
        catch (NumberFormatException numberFormatException){
            System.out.println("The XML is not readable");
        }
        catch(IllegalArgumentException illegalArgumentException){
            System.out.println("The enum format is not readable");
        }
        catch(IOException ioe)
        {
            System.out.println("I/O Exception: " + ioe);
        }
        catch (NullPointerException pointerException){
            System.out.println("Unable to read file");
        } catch (NotSatisyingParametrException e) {
            e.printStackTrace();
        } finally
        {
            try{
                if(bis != null && fis!=null)
                {
                    fis.close();
                    bis.close();
                }
            }catch(IOException ioe)
            {
                System.out.println("Error in InputStream close(): " + ioe);
            }
        }
    }
}