package src;

import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.vecmath.Color3f;
import com.sun.j3d.utils.geometry.Box;
import java.awt.Color;



public class lab4_MainBox {
    public static Box getBox(float xdim, float ydim, float zdim) {
        return new Box(xdim, ydim, zdim, getBoxAppearence());
    }

    private static Appearance getBoxAppearence() {
        Appearance ap = new Appearance();
        Color3f emissive = new Color3f(Color.decode("#3A1B0D"));
        Color3f ambient = new Color3f(Color.decode("#3A1B0D"));
        Color3f diffuse = new Color3f(Color.decode("#3A1B0D"));
        Color3f specular = new Color3f(Color.decode("#3A1B0D"));
        ap.setMaterial(new Material(ambient, emissive, diffuse, specular, 1.0f));
        return ap;
    }
}
