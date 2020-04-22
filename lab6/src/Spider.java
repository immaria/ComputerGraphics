package src;
import javax.vecmath.*;

import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import com.sun.j3d.utils.behaviors.vp.*;

import javax.swing.JFrame;
import com.sun.j3d.loaders.*;
import com.sun.j3d.loaders.objectfile.*;
import java.util.Hashtable;
import java.util.Enumeration;

public class Spider extends JFrame{
    //The canvas to be drawn upon.
    public Canvas3D myCanvas3D;
    private static BranchGroup root;
    public Spider(){
        //механізм для закриття вікна та виходу з програми
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //параметри перегляду сцени за замовчанням
        myCanvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());

        //створення SimpleUniverse (віртуального всесвіту)
        SimpleUniverse simpUniv = new SimpleUniverse(myCanvas3D);


        root = new BranchGroup();
        addImageBackground();
        simpUniv.addBranchGraph(root);

        simpUniv.getViewingPlatform().setNominalViewingTransform();
        createSceneGraph(simpUniv);
        addLight(simpUniv);


        OrbitBehavior ob = new OrbitBehavior(myCanvas3D);
        ob.setSchedulingBounds(new BoundingSphere(new Point3d(0.0,0.0,0.0),Double.MAX_VALUE));
        simpUniv.getViewingPlatform().setViewPlatformBehavior(ob);


        setTitle("Black Widow");
        setSize(700,700);
        getContentPane().add("Center", myCanvas3D);
        setVisible(true);
    }
    private void addImageBackground() {
        TextureLoader t = new TextureLoader("/home/masha/Desktop/Lab6/resources/image/spiderweb.jpg", myCanvas3D);
        Background background = new Background(t.getImage());
        background.setImageScaleMode(Background.SCALE_FIT_ALL);
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
        background.setApplicationBounds(bounds);
        root.addChild(background);
    }


    public void createSceneGraph(SimpleUniverse su){
        ObjectFile f = new ObjectFile(ObjectFile.RESIZE);
        Scene widowScene = null;
        try{
            widowScene = f.load("/home/masha/Desktop/Lab6/resources/black_widow.obj");
        }
        catch (Exception e){
            System.out.println("File loading failed:" + e);
        }

        Transform3D scaling = new Transform3D();
        scaling.setScale(1.0/6);
        Transform3D tfRoach = new Transform3D();
        tfRoach.rotX(3*Math.PI/2);
        tfRoach.mul(scaling);
        TransformGroup tgRoach = new TransformGroup(tfRoach);
        TransformGroup sceneGroup = new TransformGroup();

        Hashtable roachNamedObjects = widowScene.getNamedObjects();
        Enumeration enumer = roachNamedObjects.keys();
        String name;
        while (enumer.hasMoreElements()){
            name = (String) enumer.nextElement();
            System.out.println("Name: "+name);
        }
        BoundingSphere bounds = new BoundingSphere(new Point3d(120.0,250.0,100.0),Double.MAX_VALUE);
        BranchGroup theScene = new BranchGroup();


        TransformGroup tgBody = new TransformGroup();
        Shape3D body_widow = (Shape3D) roachNamedObjects.get("blkw_body");
        tgBody.addChild(body_widow.cloneTree());


        // -------------------------------Animation------------------------------------------------------------------

        int noRotHour = 100; // rotation quantity
        int timeRotationHour = 300; // time of 1 rotation

        BoundingSphere bs = new BoundingSphere(new Point3d(0.0,0.0,0.0),Double.MAX_VALUE);
        int timeStart = 0;

        Alpha leg1_1RotAlpha = new Alpha(noRotHour,Alpha.INCREASING_ENABLE,timeStart,0,timeRotationHour,
                0,0,0,0,0);

        Shape3D leg1_1 = (Shape3D) roachNamedObjects.get("leg1");
        TransformGroup tgLeg1_1 = new TransformGroup();
        tgLeg1_1.addChild(leg1_1.cloneTree());

        Transform3D legRotAxis = new Transform3D();
        legRotAxis.rotZ(Math.PI/2);

        RotationInterpolator leg1_1Rotation = new RotationInterpolator(leg1_1RotAlpha,tgLeg1_1,legRotAxis,(float) Math.PI/2,0.0f);
        leg1_1Rotation.setSchedulingBounds(bs);
        tgLeg1_1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        tgLeg1_1.addChild(leg1_1Rotation);

        Alpha leg2_1RotAlpha = new Alpha(noRotHour,Alpha.INCREASING_ENABLE,100,0,timeRotationHour,
                0,0,0,0,0);

        Shape3D leg2_1 = (Shape3D) roachNamedObjects.get("leg2");
        TransformGroup tgLeg2_1 = new TransformGroup();
        tgLeg2_1.addChild(leg2_1.cloneTree());

        Transform3D leg2RotAxis = new Transform3D();

        RotationInterpolator leg2_1Rotation = new RotationInterpolator(leg2_1RotAlpha,tgLeg2_1,leg2RotAxis,(float) Math.PI/8,0.0f);
        leg2_1Rotation.setSchedulingBounds(bs);
        tgLeg2_1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        tgLeg2_1.addChild(leg2_1Rotation);

        Alpha leg3_1RotAlpha = new Alpha(noRotHour,Alpha.INCREASING_ENABLE,200,0,timeRotationHour,
                0,0,0,0,0);

        Shape3D leg3_1 = (Shape3D) roachNamedObjects.get("leg3");
        TransformGroup tgLeg3_1 = new TransformGroup();
        tgLeg3_1.addChild(leg3_1.cloneTree());


        RotationInterpolator leg3_1Rotation = new RotationInterpolator(leg3_1RotAlpha,tgLeg3_1,leg2RotAxis,(float) Math.PI/8,0.0f);
        leg3_1Rotation.setSchedulingBounds(bs);
        tgLeg3_1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        tgLeg3_1.addChild(leg3_1Rotation);

        Alpha leg4_1RotAlpha = new Alpha(noRotHour,Alpha.INCREASING_ENABLE,300,0,timeRotationHour,
                0,0,0,0,0);

        Shape3D leg4_1 = (Shape3D) roachNamedObjects.get("leg4");
        TransformGroup tgLeg4_1 = new TransformGroup();
        tgLeg4_1.addChild(leg4_1.cloneTree());

        RotationInterpolator leg4_1Rotation = new RotationInterpolator(leg4_1RotAlpha,tgLeg4_1,leg2RotAxis,(float) Math.PI/8,0.0f);
        leg4_1Rotation.setSchedulingBounds(bs);
        tgLeg4_1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        tgLeg4_1.addChild(leg4_1Rotation);

        Alpha leg1_2RotAlpha = new Alpha(noRotHour,Alpha.INCREASING_ENABLE,200,0,timeRotationHour,
                0,0,0,0,0);

        Shape3D leg1_2 = (Shape3D) roachNamedObjects.get("leg8");
        TransformGroup tgLeg1_2 = new TransformGroup();
        tgLeg1_2.addChild(leg1_2.cloneTree());
        ;

        RotationInterpolator leg1_2Rotation = new RotationInterpolator(leg1_2RotAlpha,tgLeg1_2,legRotAxis,(float) Math.PI/2,0.0f);
        leg1_2Rotation.setSchedulingBounds(bs);
        tgLeg1_2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        tgLeg1_2.addChild(leg1_2Rotation);

        Alpha leg2_2RotAlpha = new Alpha(noRotHour,Alpha.INCREASING_ENABLE,300,0,timeRotationHour,
                0,0,0,0,0);

        Shape3D leg2_2 = (Shape3D) roachNamedObjects.get("leg7");
        TransformGroup tgLeg2_2 = new TransformGroup();
        tgLeg2_2.addChild(leg2_2.cloneTree());

        RotationInterpolator leg2_2Rotation = new RotationInterpolator(leg2_2RotAlpha,tgLeg2_2,leg2RotAxis,-(float) Math.PI/8,0.0f);
        leg2_2Rotation.setSchedulingBounds(bs);
        tgLeg2_2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        tgLeg2_2.addChild(leg2_2Rotation);

        Alpha leg3_2RotAlpha = new Alpha(noRotHour,Alpha.INCREASING_ENABLE,400,0,timeRotationHour,
                0,0,0,0,0);

        Shape3D leg3_2 = (Shape3D) roachNamedObjects.get("leg6");
        TransformGroup tgLeg3_2 = new TransformGroup();
        tgLeg3_2.addChild(leg3_2.cloneTree());


        RotationInterpolator leg3_2Rotation = new RotationInterpolator(leg3_2RotAlpha,tgLeg3_2,leg2RotAxis,-(float) Math.PI/8,0.0f);
        leg3_2Rotation.setSchedulingBounds(bs);
        tgLeg3_2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        tgLeg3_2.addChild(leg3_2Rotation);

        Alpha leg4_2RotAlpha = new Alpha(noRotHour,Alpha.INCREASING_ENABLE,500,0,timeRotationHour,
                0,0,0,0,0);

        Shape3D leg4_2 = (Shape3D) roachNamedObjects.get("leg5");
        TransformGroup tgLeg4_2 = new TransformGroup();
        tgLeg4_2.addChild(leg4_2.cloneTree());

        RotationInterpolator leg4_2Rotation = new RotationInterpolator(leg4_2RotAlpha,tgLeg4_2,leg2RotAxis,-(float) Math.PI/8,0.0f);
        leg4_2Rotation.setSchedulingBounds(bs);
        tgLeg4_2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        tgLeg4_2.addChild(leg4_2Rotation);

        sceneGroup.addChild(tgLeg1_1);
        sceneGroup.addChild(tgLeg2_1);
        sceneGroup.addChild(tgLeg3_1);
        sceneGroup.addChild(tgLeg4_1);
        sceneGroup.addChild(tgLeg1_2);
        sceneGroup.addChild(tgLeg2_2);
        sceneGroup.addChild(tgLeg3_2);
        sceneGroup.addChild(tgLeg4_2);

        sceneGroup.addChild(tgBody.cloneTree());

        Transform3D tCrawl = new Transform3D();
        tCrawl.rotY(-Math.PI/2);

        long crawlTime = 10000;
        Alpha crawlAlpha = new Alpha(1,
                Alpha.INCREASING_ENABLE,
                0,
                0, crawlTime,0,0,0,0,0);

        float crawlDistance = 9.0f;
        PositionInterpolator posICrawl = new PositionInterpolator(crawlAlpha,
                sceneGroup,tCrawl, -9.0f, crawlDistance);

        posICrawl.setSchedulingBounds(bs);
        sceneGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        sceneGroup.addChild(posICrawl);
        tgRoach.addChild(sceneGroup);
        theScene.addChild(tgRoach);

        Background bg = new Background(new Color3f(1.0f,1.0f,1.0f));
        bg.setApplicationBounds(bounds);
        theScene.addChild(bg);
        theScene.compile();

        su.addBranchGraph(theScene);
    }

    public void addLight(SimpleUniverse su){
        BranchGroup bgLight = new BranchGroup();
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
        Color3f lightColour1 = new Color3f(1.0f,1.0f,1.0f);
        Vector3f lightDir1 = new Vector3f(-1.0f,0.0f,-0.5f);
        DirectionalLight light1 = new DirectionalLight(lightColour1, lightDir1);
        light1.setInfluencingBounds(bounds);
        bgLight.addChild(light1);
        su.addBranchGraph(bgLight);
    }
}
