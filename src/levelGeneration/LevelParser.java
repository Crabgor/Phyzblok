package levelGeneration;

import entities.Entity;
import entities.MainEntity;
import entities.StdEntity;
import entities.WallEntity;
import org.jbox2d.common.Vec2;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
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

        float[] goalLocation = { goalLocation_x, goalLocation_y };
        Vec2 gravityVector = new Vec2(gravityVectorX, gravityVectorY);

        MainEntity mainEntity = new MainEntity(0,0,0,0,0);
        List<Entity> entities = new ArrayList<Entity>();
        JSONArray jsonEntities = jsonObject.getJSONArray("Entities");

        for (int i = 0; i < jsonEntities.length(); i++)
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
            if (i == 0) mainEntity = buildMainEntity(mass, xModel, yModel, width, height);
            else entities.add(buildEntity(mass, colour, gravityType, positionLocked, shape,
                                     xModel, yModel, width, height, rotation,
                                     frictionCoeff, restitutionCoeff, gravityScale,
                                     angularDamping, linearDamping));
        }

        return new Level(mainEntity, entities.toArray(new Entity[entities.size()]), goalLocation, gravityVector, 10); // TODO: Remove hard-coded limit
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
    private MainEntity buildMainEntity(String mass, String x, String y, String width, String height)
    {
        return new MainEntity(s_float(mass), s_float(x), s_float(y), s_float(width), s_float(height));
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
        try
        {
            if (Boolean.parseBoolean(positionLocked))
                return new WallEntity(s_float(xModel), s_float(yModel), s_float(width), s_float(height));

            else
                return new StdEntity(s_float(mass), Color.BLACK, new Rectangle(), s_float(xModel), s_float(yModel),         // TODO: Fix colour and shape params
                                     s_float(width), s_float(height), s_float(rotation), s_float(frictionCoeff),
                                     s_float(restitutionCoeff), s_float(gravityScale),
                                     s_float(linearDamping), s_float(angularDamping));
        }
        catch (Exception e)
        {
            return null;
        }
    }


    /**
     *
     * @param s
     * @return Returns a float value parsed from the string parameter
     */
    private float s_float(String s)
    {
        try
        {
            return Float.parseFloat(s);
        }
        catch (Exception e)
        {
            return 0.00f;
        }
    }
}
