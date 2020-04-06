package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.swing.Timer;
import javax.vecmath.*;



public class lab4 implements ActionListener {
    private float angle = 0;
    private TransformGroup houseTransformGroup;
    private Transform3D houseTransform3D = new Transform3D();
    private Timer timer;

    public static void main(String[] args) {
        new lab4();
    }

    public lab4() {
        timer = new Timer(50, this);
        timer.start();
        BranchGroup scene = createSceneGraph();
        SimpleUniverse u = new SimpleUniverse();
        u.getViewingPlatform().setNominalViewingTransform();
        u.addBranchGraph(scene);
    }
    public BranchGroup createSceneGraph() {
        // створюємо групу об'єктів
        BranchGroup objRoot = new BranchGroup();

        // створюємо об'єкт, що будемо додавати до групи
        houseTransformGroup = new TransformGroup();
        houseTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        buildHouse();
        objRoot.addChild(houseTransformGroup);

        // налаштовуємо освітлення
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),100.0);
        Color3f light1Color = new Color3f(1.0f, 0.5f, 0.4f);
        Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
        DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
        light1.setInfluencingBounds(bounds);
        objRoot.addChild(light1);

        // встановлюємо навколишнє освітлення
        Color3f ambientColor = new Color3f(1.0f, 1.0f, 1.0f);
        AmbientLight ambientLightNode = new AmbientLight(ambientColor);
        ambientLightNode.setInfluencingBounds(bounds);
        objRoot.addChild(ambientLightNode);
        return objRoot;
    }


    private void buildHouse() {

        // BOX --------------------------------------------------------
        TransformGroup tgBox = new TransformGroup();
        Transform3D transformBox = new Transform3D();
        Box mainBox = lab4_MainBox.getBox(0.4f, 0.4f, 0.4f);
        Vector3f vectorBox = new Vector3f(.0f, -0.20f, .0f);
        transformBox.setTranslation(vectorBox);
        tgBox.setTransform(transformBox);
        tgBox.addChild(mainBox);
        houseTransformGroup.addChild(tgBox);
        //-------------------------------------------------------------

        // ROOF -------------------------------------------------------
        TransformGroup tgRoof = new TransformGroup();
        Transform3D transformRoof = new Transform3D();
        Shape3D mainRoof = lab4_Roof.getRoof();
        Vector3f vectorRoof = new Vector3f(.0f, 0.07f, .0f);
        transformRoof.setTranslation(vectorRoof);
        tgRoof.setTransform(transformRoof);
        tgRoof.addChild(mainRoof);
        houseTransformGroup.addChild(tgRoof);
        //-------------------------------------------------------------

        // CHIMNEY ----------------------------------------------------
        TransformGroup tgChimney = new TransformGroup();
        Transform3D transformChimney = new Transform3D();
        Cylinder mainChimney = lab4_Chimney.getChimney(0.05f, 0.4f);
        Vector3f vectorChimney = new Vector3f(-0.1f, 0.4f, -0.3f);
        transformChimney.setTranslation(vectorChimney);
        tgChimney.setTransform(transformChimney);
        tgChimney.addChild(mainChimney);
        houseTransformGroup.addChild(tgChimney);
        //-------------------------------------------------------------

        // DOOR -------------------------------------------------------
        TransformGroup tgDoor = new TransformGroup();
        Transform3D transformDoor = new Transform3D();
        Box mainDoor = lab4_Door.getDoor(0.2f, 0.3f, 0.2f);
        Vector3f vectorDoor = new Vector3f(.0f, -0.30f, .22f);
        transformDoor.setTranslation(vectorDoor);
        tgDoor.setTransform(transformDoor);
        tgDoor.addChild(mainDoor);
        houseTransformGroup.addChild(tgDoor);
        //-------------------------------------------------------------

        // HANDLE -----------------------------------------------------
        TransformGroup tgHandle = new TransformGroup();
        Transform3D transformHandle = new Transform3D();
        Sphere mainHandle = lab4_Handle.getHandle(0.03f);
        Vector3f vectorHandle = new Vector3f(.1f, -0.30f, .42f);
        transformHandle.setTranslation(vectorHandle);
        tgHandle.setTransform(transformHandle);
        tgHandle.addChild(mainHandle);
        houseTransformGroup.addChild(tgHandle);
        //-------------------------------------------------------------

        // WINDOW -----------------------------------------------------
        TransformGroup tgWindow = new TransformGroup();
        Transform3D transformWindow = new Transform3D();
        Box mainWindow = lab4_Window.getWindow(0.2f, 0.2f, 0.2f);
        Vector3f vectorWindow = new Vector3f(.0f, -0.25f, -0.21f);
        transformWindow.setTranslation(vectorWindow);
        tgWindow.setTransform(transformWindow);
        tgWindow.addChild(mainWindow);
        houseTransformGroup.addChild(tgWindow);
        //-------------------------------------------------------------

        // FRAME ------------------------------------------------------
        createFrame(0.21f, 0.02f, 0.02f, .0f, -0.25f, -0.40f);
        createFrame(0.21f, 0.02f, 0.02f, .0f, -0.45f, -0.40f);
        createFrame(0.21f, 0.02f, 0.02f, .0f, -0.05f, -0.40f);
        createFrame(0.02f, 0.21f, 0.02f, .0f, -0.25f, -0.40f);
        createFrame(0.02f, 0.21f, 0.02f, .19f, -0.25f, -0.40f);
        createFrame(0.02f, 0.21f, 0.02f, -0.19f, -0.25f, -0.40f);

    }
    private void createFrame(float xdim, float ydim, float zdim, float xVector, float yVector, float zVector) {
        TransformGroup tgFrame = new TransformGroup();
        Transform3D transformFrame = new Transform3D();
        Box mainFrame = lab4_Frame.getFrame(xdim, ydim, zdim);
        Vector3f vectorFrame = new Vector3f(xVector, yVector, zVector);
        transformFrame.setTranslation(vectorFrame);
        tgFrame.setTransform(transformFrame);
        tgFrame.addChild(mainFrame);
        houseTransformGroup.addChild(tgFrame);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        houseTransform3D.rotY(angle);
        houseTransformGroup.setTransform(houseTransform3D);
        angle += 0.03;
    }
}

