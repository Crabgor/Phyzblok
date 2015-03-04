package view;

import entities.ViewEntity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cregnacht on 2015-03-03.
 */
public class PhyzShapeFactory
{
    /**
     *
     * @param entity
     * @return
     */
    public PhyzRectangle MakeRectangle(ViewEntity entity)
    {
        if (entity.getShape().toString() == new Rectangle().toString())
            return new PhyzRectangle(entity);
        return null;
    }


    /**
     *
     * @param entities
     * @return
     */
    public List<PhyzRectangle> MakeRectangles(List<ViewEntity> entities)
    {
        List<PhyzRectangle> rectangles = new ArrayList<PhyzRectangle>();
        for (ViewEntity v : entities)
            if (v.getShape().toString() == new Rectangle().toString())
                rectangles.add(new PhyzRectangle(v));
        return rectangles;
    }
}
