package levelGeneration;

import entities.Entity;
import entities.MainEntity;
import org.json.JSONArray;
import org.json.JSONObject;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Cregnacht on 2015-02-26.
 */
public class LevelParser
{
    private String[] levelMap;


    /**
     *
     * @param levelIndex
     * @return
     */
    public Level loadLevel(int levelIndex)
    {
        if (levelIndex < 0 || levelIndex > levelMap.length) return null;

        return ParseJSONLevel(levelMap[levelIndex]);    // TODO : NOTE THAT THIS RETURNS NULL IF ERROR.
    }


    /**
     *
     * @param s
     * @return
     */
    private Level ParseJSONLevel(String s)
    {
        String jsonFile = readJsonFile(s);
        if (jsonFile == null) return null;

        JSONObject jsonObject = new JSONObject(jsonFile);

        JSONObject worldInfo = jsonObject.getJSONObject("worldInfo");
        float goalLocation_x = Float.parseFloat(worldInfo.getString("goalLocation_x")),
              goalLocation_y = Float.parseFloat(worldInfo.getString("goalLocation_y")),
              gravityVectorX = Float.parseFloat(worldInfo.getString("gravityVectorX")),
              gravityVectorY = Float.parseFloat(worldInfo.getString("gravityVectorY"));

        Entity mainEntity;
        List<Entity> entities = new ArrayList<Entity>();
        JSONArray jsonEntities = jsonObject.getJSONArray("Entities");

        // CREATE MAIN ENTITY

        for (int i = 1; i < jsonEntities.length(); i++)
        {
            JSONObject jsonEntity = jsonEntities.getJSONObject(i);
            String  mass = jsonEntity.getString("mass"),
                    colour = jsonEntity.getString("colour"),
                    gravityType = jsonEntity.getString("gravityType"),
                    positionLocked = jsonEntity.getString("positionLocked"),
                    shape = jsonEntity.getString("shape"),
                    xModel = jsonEntity.getString("xModel"),
                    yModel = jsonEntity.getString("yModel"),
                    width = jsonEntity.getString("width"),
                    height = jsonEntity.getString("height"),
                    rotation = jsonEntity.getString("rotation"),
                    frictionCoeff = jsonEntity.getString("frictionCoeff"),
                    restitutionCoeff = jsonEntity.getString("restitutionCoeff"),
                    gravityScale = jsonEntity.getString("gravityScale"),
                    angularDamping = jsonEntity.getString("angularDamping"),
                    linearDamping = jsonEntity.getString("linearDamping");
            entities.add(buildEntity(mass, colour, gravityType, positionLocked, shape,
                                     xModel, yModel, width, height, rotation,
                                     frictionCoeff, restitutionCoeff, gravityScale,
                                     angularDamping, linearDamping));
        }

        return null; // TODO : FINISH IMPLEMENTATION
    }


    /**
     *
     * @param s
     * @return
     */
    private String readJsonFile(String s)
    {
        try
        {
            String json = "";
            URL url = LevelParser.class.getResource(s);
            Scanner fileScanner = new Scanner(new File(url.toURI()));
            while (fileScanner.hasNextLine())
                json.concat(fileScanner.nextLine());
            return json;
        }
        catch (Exception e)
        {
            return null;
        }
    }


    /**
     *
     * @return
     */
    private MainEntity buildMainEntity()
    {
//      TODO: FINISH IMPLEMENTATION OF buildMainEntity;
        throw new NotImplementedException();
    }


    /**
     *
     * @return
     */
    private Entity buildEntity(String mass, String colour, String gravityType, String positionLocked, String shape,
                               String xModel, String yModel, String width, String height, String rotation,
                               String frictionCoeff, String restitutionCoeff, String gravityScale,
                               String angularDamping, String linearDamping)
    {
        // TODO: FINISH IMPLEMENTATION OF buildEntity;
        throw new NotImplementedException();
    }
}
