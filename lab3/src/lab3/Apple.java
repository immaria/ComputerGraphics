package lab3;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Apple extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        Group root = new Group();
        Scene scene = new Scene(root, 600, 300);

        LinearGradient backgroundgrad = new LinearGradient(100, .5, 100, 100, false,
                CycleMethod.NO_CYCLE, new Stop(0, Color.rgb(50, 170, 175)),
                new Stop(0.9, Color.rgb(248, 246, 147)));


        scene.setFill(backgroundgrad);
        //apple stalk
        Line stalk = new Line(190, 20, 200, 35);
        stalk.setStrokeWidth(10);
        stalk.setStroke(Color.rgb(97, 51, 4));
        root.getChildren().add(stalk);
        RadialGradient gradient2 = new RadialGradient(0, .1, 100, 100, 200, false,
                CycleMethod.NO_CYCLE, new Stop(0, Color.rgb(7, 68, 28)),
                new Stop(1, Color.rgb(107, 154, 6)));

        //top leaf
        MoveTo topleaf = new MoveTo(320, 10);
        QuadCurveTo tl1 = new QuadCurveTo(330, 150, 200, 30);
        QuadCurveTo tl2 = new QuadCurveTo(230, -10, 280, 30);
        QuadCurveTo tl3 = new QuadCurveTo(320, 45, 320, 20);
        Path tleaf = new Path();
        tleaf.setStrokeWidth(2);
        tleaf.setStroke(Color.BLACK);
        tleaf.setFill(gradient2);
        tleaf.getElements().addAll(topleaf, tl1, tl2,tl3);
        root.getChildren().add(tleaf);

        // top leaf line
        MoveTo topleafline = new MoveTo(320, 30);
        QuadCurveTo tll = new QuadCurveTo(290, 80, 200, 30);
        Path tlel = new Path();
        tlel.setStroke(Color.BLACK);
        tlel.setStrokeWidth(2);
        tlel.getElements().addAll(topleafline, tll);
        root.getChildren().add(tlel);

        MoveTo leafLine1 = new MoveTo(240, 55);
        QuadCurveTo ll1 = new QuadCurveTo(270, 35, 260, 40);
        Path leafl1 = new Path();
        leafl1.getElements().addAll(leafLine1, ll1);
        root.getChildren().add(leafl1);

        MoveTo leafLine2 = new MoveTo(260, 55);
        QuadCurveTo ll2 = new QuadCurveTo(290, 35, 280, 40);
        Path leafl2 = new Path();
        leafl2.getElements().addAll(leafLine2, ll2);
        root.getChildren().add(leafl2);

        MoveTo leafLine3 = new MoveTo(280, 55);
        QuadCurveTo ll3 = new QuadCurveTo(310, 35, 300, 40);
        Path leafl3 = new Path();
        leafl3.getElements().addAll(leafLine3, ll3);
        root.getChildren().add(leafl3);

        //  apple shape
        Circle C = new Circle(200,150,100);
        RadialGradient gradient1 = new RadialGradient(100, .6, 100, 120, 200, false, CycleMethod.NO_CYCLE,
                new Stop(0.3, Color.rgb(246, 45, 6)), new Stop(0.8, Color.rgb(255, 254, 44)), new Stop(1, Color.rgb(149, 215, 10)));
        C.setFill(gradient1);
        C.setStrokeWidth(3);
        C.setStroke(Color.BLACK);
        root.getChildren().add(C);

        //bottom leaf
        MoveTo bottomLeaf = new MoveTo(250, 120);
        QuadCurveTo qt1 = new QuadCurveTo(80, 60, 200, 30);
        QuadCurveTo qt2 = new QuadCurveTo(280, 40, 250, 120);
        Path bottom = new Path();
        bottom.setStrokeWidth(2);
        bottom.setStroke(Color.BLACK);
        bottom.setFill(gradient2);
        bottom.getElements().addAll(bottomLeaf, qt1, qt2);
        root.getChildren().add(bottom);

        //leaf line
        MoveTo leafline = new MoveTo(250, 120);
        QuadCurveTo ll = new QuadCurveTo(250, 60, 200, 30);
        Path lel = new Path();
        lel.setStrokeWidth(2);
        lel.setStroke(Color.BLACK);
        lel.getElements().addAll(leafline, ll);
        root.getChildren().add(lel);

        //the left eye
        Ellipse leftEye = new Ellipse(180, 170, 10, 15);
        leftEye.setFill(Color.WHITE);
        root.getChildren().add(leftEye);

        Ellipse leftСornea = new Ellipse(183, 175, 7, 10);
        leftСornea.setFill(Color.BLACK);
        root.getChildren().add(leftСornea);

        Ellipse leftPupil = new Ellipse(184, 179, 4, 6);
        leftPupil.setFill(Color.WHITE);
        root.getChildren().add(leftPupil);

        // the right one
        Ellipse rightEye = new Ellipse(220, 170, 10, 15);
        rightEye.setFill(Color.WHITE);
        root.getChildren().add(rightEye);

        Ellipse rightСornea = new Ellipse(223, 175, 7, 10);
        rightСornea.setFill(Color.BLACK);
        root.getChildren().add(rightСornea);

        Ellipse rightPupil = new Ellipse(224, 179, 4, 6);
        rightPupil.setFill(Color.WHITE);
        root.getChildren().add(rightPupil);

        //eyebrows
        Line leftEyebrow = new Line(170, 135, 180, 133);
        root.getChildren().add(leftEyebrow);

        Line rightEyebrow = new Line(215, 133, 226, 134);
        root.getChildren().add(rightEyebrow);

        //nose
        MoveTo n = new MoveTo(200, 190);
        QuadCurveTo nose = new QuadCurveTo(215, 195, 200, 200);
        Path ns = new Path();
        ns.getElements().addAll(n, nose);
        root.getChildren().add(ns);

        //smile
        MoveTo s = new MoveTo(180, 210);
        QuadCurveTo smile = new QuadCurveTo(200, 220, 220, 210);
        Path sm = new Path();
        sm.getElements().addAll(s, smile);
        root.getChildren().add(sm);

        MoveTo smLine = new MoveTo(175, 212);
        QuadCurveTo smL = new QuadCurveTo(180, 210, 183, 205);
        Path smLn = new Path();
        smLn.getElements().addAll(smLine, smL);
        root.getChildren().add(smLn);

        //chin
        Line chin = new Line(200, 230, 200, 235);
        root.getChildren().add(chin);

        //arms
        //the left one
        MoveTo leftarm = new MoveTo(100, 135);
        QuadCurveTo lrm1 = new QuadCurveTo(130, 155, 100, 165);
        QuadCurveTo lrm2 = new QuadCurveTo(80, 185, 120, 195);
        Path la = new Path();
        la.setStrokeWidth(3);
        la.setStroke(Color.BLACK);
        la.getElements().addAll(leftarm, lrm1, lrm2);
        root.getChildren().add(la);

        //left wrist
        MoveTo lll1 = new MoveTo(125, 175);
        QuadCurveTo lin1 = new QuadCurveTo(120, 185, 120, 195);
        QuadCurveTo lin2 = new QuadCurveTo(135, 205, 153, 195);
        QuadCurveTo lin3 = new QuadCurveTo(160, 190, 148, 190);
        QuadCurveTo lin4 = new QuadCurveTo(155, 155, 140, 175);
        QuadCurveTo lin5 = new QuadCurveTo(140, 165, 130, 175);
        QuadCurveTo lin6 = new QuadCurveTo(130, 165, 125, 175);
        Path ln1 = new Path();
        ln1.setStrokeWidth(2);
        ln1.setStroke(Color.BLACK);
        ln1.setFill(gradient2);
        ln1.getElements().addAll(lll1, lin1, lin2, lin3, lin4, lin5, lin6);
        root.getChildren().add(ln1);

        //the right arm
        MoveTo rightarm = new MoveTo(300, 135);
       QuadCurveTo rrm1 = new QuadCurveTo(320, 155, 300, 165);
        QuadCurveTo rrm2 = new QuadCurveTo(290, 185, 310, 195);
        Path ra = new Path();
        ra.setStrokeWidth(3);
        ra.setStroke(Color.BLACK);
        ra.getElements().addAll(rightarm, rrm1, rrm2);
        root.getChildren().add(ra);

        //right wrist
        MoveTo rll1 = new MoveTo(325, 175);
        QuadCurveTo rin1 = new QuadCurveTo(320, 185, 310, 195);
        QuadCurveTo rin2 = new QuadCurveTo(335, 205, 353, 195);
        QuadCurveTo rin3 = new QuadCurveTo(360, 190, 348, 190);
        QuadCurveTo rin4 = new QuadCurveTo(355, 155, 340, 175);
        QuadCurveTo rin5 = new QuadCurveTo(340, 165, 330, 175);
        QuadCurveTo rin6 = new QuadCurveTo(330, 165, 325, 175);
        Path rn1 = new Path();
        rn1.setStrokeWidth(2);
        rn1.setStroke(Color.BLACK);
        rn1.setFill(gradient2);
        rn1.getElements().addAll(rll1, rin1, rin2, rin3, rin4, rin5, rin6);
        root.getChildren().add(rn1);


        // some additional lines
        //right side
        MoveTo addLine1 = new MoveTo(270, 100);
        QuadCurveTo ad1 = new QuadCurveTo(290, 135, 280, 170);
        Path addpath1 = new Path();
        addpath1.getElements().addAll(addLine1, ad1);
        root.getChildren().add(addpath1);

        MoveTo addLine2 = new MoveTo(285, 120);
        QuadCurveTo ad2 = new QuadCurveTo(300, 155, 275, 190);
        Path addpath2 = new Path();
        addpath2.getElements().addAll(addLine2, ad2);
        root.getChildren().add(addpath2);

        MoveTo addLine3 = new MoveTo(260, 100);
        QuadCurveTo ad3 = new QuadCurveTo(290, 150, 270, 180);
        Path addpath3 = new Path();
        addpath3.getElements().addAll(addLine3, ad3);
        root.getChildren().add(addpath3);


        //left side
        MoveTo addLine4 = new MoveTo(150, 80);
        QuadCurveTo ad4 = new QuadCurveTo(100, 115, 125, 150);
        Path addpath4 = new Path();
        addpath4.getElements().addAll(addLine4, ad4);
        root.getChildren().add(addpath4);

        MoveTo addLine5 = new MoveTo(170, 83);
        QuadCurveTo ad5 = new QuadCurveTo(120, 100, 130, 140);
        Path addpath5 = new Path();
        addpath5.getElements().addAll(addLine5, ad5);
        root.getChildren().add(addpath5);

        MoveTo addLine6 = new MoveTo(133, 100);
        QuadCurveTo ad6 = new QuadCurveTo(110, 140, 140, 160);
        Path addpath6 = new Path();
        addpath6.getElements().addAll(addLine6, ad6);
        root.getChildren().add(addpath6);



        // Animation
        int cycleCount = 1;
        int time = 2000;

        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(time), root);
        scaleTransition.setToX(2);
        scaleTransition.setToY(2);
        scaleTransition.setAutoReverse(true);

        RotateTransition rotateTransition = new RotateTransition(Duration.millis(time), root);
        rotateTransition.setByAngle(360f);
        rotateTransition.setCycleCount(cycleCount);
        rotateTransition.setAutoReverse(true);

        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(time), root);
        translateTransition.setFromX(150);
        translateTransition.setToX(50);
        translateTransition.setCycleCount(cycleCount + 1);
        translateTransition.setAutoReverse(true);

        TranslateTransition translateTransition2 = new TranslateTransition(Duration.millis(time), root);
        translateTransition2.setFromX(50);
        translateTransition2.setToX(150);
        translateTransition2.setCycleCount(cycleCount + 1);
        translateTransition2.setAutoReverse(true);

        ScaleTransition scaleTransition2 = new ScaleTransition(Duration.millis(time), root);
        scaleTransition2.setToX(0.5);
        scaleTransition2.setToY(0.5);
        scaleTransition2.setCycleCount(cycleCount);
        scaleTransition2.setAutoReverse(true);

        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(
                scaleTransition,
                scaleTransition2,
                translateTransition,
                rotateTransition
        );
        parallelTransition.setCycleCount(Timeline.INDEFINITE);
        parallelTransition.play();


        primaryStage.setResizable(false);
        primaryStage.setTitle("Lab №3, var №1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
