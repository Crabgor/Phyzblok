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
        if (entity.getShape().getClass() == new Rectangle().getClass())
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
            if (v.getShape().getClass() == new Rectangle().getClass())
                rectangles.add(new PhyzRectangle(v));
        return rectangles;
    }
}
