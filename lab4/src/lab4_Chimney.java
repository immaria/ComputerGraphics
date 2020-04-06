package src;

import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.vecmath.Color3f;
import com.sun.j3d.utils.geometry.Cylinder;
import java.awt.Color;

public class lab4_Chimney {
    public static Cylinder getChimney(float var1, float var2) {
        return new Cylinder(var1, var2, getChimneyAppearence());
    }

    private static Appearance getChimneyAppearence() {
        Appearance ap = new Appearance();
        Color3f emissive = new Color3f(Color.decode("#210F07"));
        Color3f ambient = new Color3f(Color.decode("#210F07"));
        Color3f diffuse = new Color3f(Color.decode("#210F07"));
        Color3f specular = new Color3f(Color.decode("#210F07"));
        ap.setMaterial(new Material(ambient, emissive, diffuse, specular, 1.0f));
        return ap;
    }
}
