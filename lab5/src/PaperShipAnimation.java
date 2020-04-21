package src;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.*;

public class PaperShipAnimation implements ActionListener, KeyListener {

    private TransformGroup ship;
    private Transform3D transform3D = new Transform3D();

    private float x = 0;
    private float y = 0;

    private boolean w = false;
    private boolean s = false;
    private boolean a = false;
    private boolean d = false;
    private boolean e = false;
    private  boolean q = false;
    private boolean z = false;
    private  boolean c = false;
    private boolean r = false;
    private  boolean t = false;
    private boolean n = false;
    private  boolean m = false;
    private  boolean l = false;
    private float zoom = 0.2f;

    public PaperShipAnimation(TransformGroup ship) {
        this.ship = ship;
        this.ship.getTransform(this.transform3D);

        Timer timer = new Timer(20, this);
        timer.start();
    }

    private void Move() {
  	  	if (w) {
  	  		y += 0.01f;
  	  	}
  	  	if (s) {
			y -= 0.01f;

	  	}
        if (a) {
			x -= 0.01f;
        }

        if (d) {
        	x += 0.01f;
        }

		transform3D.setTranslation(new Vector3f(x, y, 0));

        if (e){
				Transform3D rotation = new Transform3D();
				rotation.rotY(0.05f);
				transform3D.mul(rotation);

		}

		if (q){

			Transform3D rotation = new Transform3D();
			rotation.rotY(-0.05f);
			transform3D.mul(rotation);

		}

        if (z){
            Transform3D rotation1 = new Transform3D();
            rotation1.rotZ(0.05f);
            transform3D.mul(rotation1);

        }

        if (c){

            Transform3D rotation2 = new Transform3D();
            rotation2.rotZ(-0.05f);
            transform3D.mul(rotation2);

        }

        if (r){
            Transform3D rotation1 = new Transform3D();
            rotation1.rotX(0.05f);
            transform3D.mul(rotation1);

        }

        if (t){

            Transform3D rotation2 = new Transform3D();
            rotation2.rotX(-0.05f);
            transform3D.mul(rotation2);
        }
        if (n) {
            zoom += 0.02f;
            transform3D.setScale(new Vector3d(zoom, zoom, zoom));
        }
        if (m) {
            zoom -= 0.02f;
            if(zoom <= 0) {
                zoom = 3.f;
            }
            transform3D.setScale(new Vector3d(zoom, zoom, zoom));
        }


        ship.setTransform(transform3D);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    	Move();
    }
    
    @Override
    public void keyPressed(KeyEvent ev) {
    	switch (ev.getKeyChar()) {
	    	case 'w': w = true; break;
	    	case 's': s = true; break;
	    	case 'a': a = true; break;
	    	case 'd': d = true; break;
	    	case 'e': e = true; break;
	    	case 'q': q = true; break;
            case 'z': z = true; break;
            case 'c': c = true; break;
            case 'r': r = true; break;
            case 't': t = true; break;
            case 'n': n = true; break;
            case 'm': m = true; break;
            case 'l': l = false; break;
		}
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    	
    }
    
    @Override
    public void keyReleased(KeyEvent ev) {
    	switch (ev.getKeyChar()) {
	    	case 'w': w = false; break;
	    	case 's': s = false; break;
	    	case 'a': a = false; break;
	    	case 'd': d = false; break;
			case 'e': e = false; break;
			case 'q': q = false; break;
            case 'z': z = false; break;
            case 'c': c = false; break;
            case 'r': r = false; break;
            case 't': t = false; break;
            case 'n': n = false; break;
            case 'm': m = false; break;
            case 'l': l = false; break;

    	}
    }
}
