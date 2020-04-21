package src;

import com.sun.j3d.utils.universe.*;

import javax.media.j3d.*;
import javax.vecmath.*;
import javax.media.j3d.Background;

import com.sun.j3d.loaders.*;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.image.TextureLoader;

import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import javax.swing.JFrame;

public class PaperShip extends JFrame {
	public static Canvas3D canvas;
	private static SimpleUniverse universe;
	private static BranchGroup root;
	
	private static TransformGroup ship;
	
    public PaperShip() throws IOException {
    	configureWindow();
        configureCanvas();
        configureUniverse();
        
        root = new BranchGroup();
        addImageBackground();
        addDirectionalLightToUniverse();
        addAmbientLightToUniverse();
        ChangeViewAngle();

        ship = getShipGroup();
        root.addChild(ship);
        
        root.compile();
        universe.addBranchGraph(root);
    }
    
    // start initialization
    
    private void configureWindow() {
        setTitle("Paper ship animation");
        setSize(760,640);
      //  setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void configureCanvas() {
        canvas=new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        canvas.setDoubleBufferEnable(true);
        getContentPane().add(canvas,BorderLayout.CENTER);
    }
    
    private void configureUniverse() {
        root= new BranchGroup();
        universe = new SimpleUniverse(canvas);
        universe.getViewingPlatform().setNominalViewingTransform();
    }
    
    private void addImageBackground() {
        TextureLoader t = new TextureLoader("/home/masha/ComputerGraphics/lab5/media/puddle.jpg", canvas);
        Background background = new Background(t.getImage());
        background.setImageScaleMode(Background.SCALE_FIT_ALL);
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
        background.setApplicationBounds(bounds);
        root.addChild(background);
    }
    
    private void addDirectionalLightToUniverse() {
        Bounds bounds = new BoundingSphere();
//        Color3f c = new Color3f(Color.RED);
//        Color3f color = new Color3f(125/255f, 40/255f, 35/255f);
        Vector3f lightdirection = new Vector3f(-2f,-2f,-2f);
        DirectionalLight dirlight = new DirectionalLight(new Color3f(Color.lightGray),lightdirection);
        dirlight.setInfluencingBounds(bounds);
        root.addChild(dirlight);
	}
    
    private void addAmbientLightToUniverse() {
        AmbientLight light = new AmbientLight(new Color3f(1, 1, 1));
        light.setInfluencingBounds(new BoundingSphere());
        root.addChild(light);
    }

    private void ChangeViewAngle() {
        ViewingPlatform vp = universe.getViewingPlatform();
        TransformGroup vpGroup = vp.getMultiTransformGroup().getTransformGroup(0);
        Transform3D vpTranslation = new Transform3D();
        vpTranslation.setTranslation(new Vector3f(0, 0, 6));
        vpGroup.setTransform(vpTranslation);
    }

    private TransformGroup getShipGroup() throws IOException {
    	Shape3D shape = getModelShape3D("19889_junk__ship_", "/home/masha/ComputerGraphics/lab5/media/ship.obj");

    	Transform3D transform3D = new Transform3D();
    	transform3D.setScale(new Vector3d(0.2, 0.2, 0.2));

        Transform3D rotationX = new Transform3D();
        rotationX.rotX(-Math.PI/2);
        rotationX.setScale(3.5);
        transform3D.mul(rotationX);
        addAppearance(shape);

        TransformGroup group = getModelGroup(shape);
        group.setTransform(transform3D);

        return group;
    }

    private TransformGroup getModelGroup(Shape3D shape) {
    	TransformGroup group = new TransformGroup();
    	group.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    	group.addChild(shape);
    	return group;
    }
    
	private Shape3D getModelShape3D(String name, String path) throws IOException {
    	Scene scene = getSceneFromFile(path);
    	Map<String, Shape3D> map = scene.getNamedObjects();
    	printModelElementsList(map);
    	Shape3D shape = map.get(name);
    	scene.getSceneGroup().removeChild(shape);
    	return shape;
    }
    
    private Scene getSceneFromFile(String path) throws IOException {
        ObjectFile file = new ObjectFile(ObjectFile.RESIZE);
        file.setFlags(ObjectFile.RESIZE | ObjectFile.TRIANGULATE | ObjectFile.STRIPIFY);
        return file.load(new FileReader(path));
    }
    
    private void printModelElementsList(Map<String, Shape3D> map) {
    	for (String name : map.keySet()) {
    		System.out.println("Object name: " + name);
    	}
    }

    private void addAppearance(Shape3D shape) throws IOException {
        Appearance appearance = new Appearance();
        appearance.setTexture(getTexture("/home/masha/ComputerGraphics/lab5/media/newspaper.jpg"));
        appearance.setMaterial(getMaterial());
        shape.setAppearance(appearance);
    }

    Material getMaterial() {
        Material material = new Material();
        material.setAmbientColor ( new Color3f( 0.9f, 0.9f, 0.9f) );
        material.setDiffuseColor ( new Color3f( 1f, 1f, 1f ) );
        material.setSpecularColor( new Color3f( 1f, 1f, 1f ) );
        material.setShininess( 0.5f );
        material.setLightingEnable(true);
        return material;
    }



    private Texture getTexture(String path) {
        TextureLoader textureLoader = new TextureLoader(path,"LUMINANCE",canvas);
        Texture texture = textureLoader.getTexture();
        texture.setBoundaryModeS(Texture.WRAP);
        texture.setBoundaryModeT(Texture.WRAP);
   //     texture.setBoundaryColor( new Color4f( 0.0f, 1.0f, 0.0f, 0.0f ) );
        return texture;
    }
    
    // end main
    
    
    public static void main(String[] args) {
        try {
            PaperShip window = new PaperShip();
           PaperShipAnimation boatMovement = new PaperShipAnimation(ship);
            canvas.addKeyListener(boatMovement);
            window.setVisible(true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
