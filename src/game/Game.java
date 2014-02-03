package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

import sprites.Boss;
import sprites.Bullet;
import sprites.Enemy;
import sprites.Jet;
import sprites.Player;

/**
 * Class: Game
 * 
 * @author Jason Aydelotte
 * @author Marcus Rogers
 * @version 1.0 Course : ITEC 3150-01 Spring 2013 Written: Feburary, 2013 Note:
 *          Received help from Dr. Im and most of the code here is written by
 *          Dr.Im. This class- This is the game screen that gets tacked on the
 *          JFrame. Notice that this gets drawn many times per second.(Im)
 *          Purpose: Project 1
 */

public class Game extends Canvas implements Runnable, KeyListener {

	TankGameFrame reset = new TankGameFrame();
	private static int score = 0;
	private Jet jA;
	private Jet jT;
	private Enemy e;
	private Player p;
	private Boss be;
	private List<Bullet> bullets = new ArrayList<Bullet>();
	private List<Enemy> enemies = new ArrayList<Enemy>();
	private List<Jet> jets = new ArrayList<Jet>();
	private List<Jet> jetsTracking = new ArrayList<Jet>();
	private List<Boss> bossEnemy = new ArrayList<Boss>();
	private Input input = new Input();
	private KeyEvent k;
	private int time = 0;
	private int scoreHit = 0;
	private int tankHit = 0;
	private int tickCount;
	private double scrollTick;
	private double jetTickA;
	private double jetTickB;
	private int backgroundScrollX;
	private Image tank;
	private Image turret;
	private Image background;
	private Image boss;
	private Image jet;
	private Image explosion;
	private Random jSpawn = new Random();
	private int hitCount = 0;
	private int bossHitCount = 0;
	private static boolean newRun = true;
	private Menu menu;
	private Controls controls;
	private GameOver gameOver;
	private WinGame winGame;
	private boolean levelOne;
	private boolean levelTwo;
	private boolean levelThree;
	private boolean stopScrolling = false;
	private boolean gameWon = false;
	private boolean gameLost = false;
	public static final int GAME_WIDTH = 500;
	public static final int GAME_HEIGHT = 500;
	private static double shotsFired;
	private static double enemiesHit;
	private static double accuracy;

	public Game() throws IOException {

		try {
			tank = ImageIO.read(Game.class
					.getResource("/res/images/Images/Tank_tank.gif"));
			turret = ImageIO.read(Game.class
					.getResource("/res/images/Images/Tank_turret01.gif"));
			background = ImageIO.read(Game.class
					.getResource("/res/images/Images/Tank_background2.gif"));
			jet = ImageIO.read(Game.class
					.getResource("/res/images/Images/Tank_jet.gif"));
			boss = ImageIO.read(Game.class
					.getResource("/res/images/Images/Tank_boss.gif"));
			explosion = ImageIO.read(Game.class
					.getResource("/res/images/Images/Tank_turret09.gif"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		// create a player and enemy
		// backbuffer = new BufferedImage(640, 480, BufferedImage.TYPE_INT_RGB);
		// g2d = backbuffer.createGraphics();

		p = new Player(250, 400, tank);
		e = new Enemy(460, 0, turret, 5);
		be = new Boss(455, -1000, boss, 5);
		enemies.add(e);
		bossEnemy.add(be);
		levelOne = true;
		levelTwo = false;
		levelThree = false;
		menu = new Menu();
		controls = new Controls();
		gameOver = new GameOver();
		winGame = new WinGame();

		addKeyListener(this);
	}

	public static double getShotsFired() {
		return shotsFired;
	}

	public void incShotsFired() {
		shotsFired++;
	}

	public static double getEnemiesHit() {
		return enemiesHit;
	}

	public static void incEnemiesHit() {
		enemiesHit++;
	}

	public static double getAccuracy() {
		accuracy = Math.round(((getEnemiesHit() / getShotsFired()) * 100));
		return accuracy;
	}

	public static void setNewRun() {
		newRun = false;
	}

	public int getTankHit() {
		return tankHit;
	}

	// setter for the tank hit collision
	public void setTankHit(int tankHit) {
		this.tankHit = tankHit;
	}



	// class Clip was give to me by Dr.Im
	private static Clip loadSound(String name) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataInputStream dis = new DataInputStream(
					Game.class.getResourceAsStream(name));
			byte[] buffer = new byte[1024];
			int read = 0;
			while ((read = dis.read(buffer)) >= 0) {
				baos.write(buffer, 0, read);
			}
			dis.close();
			byte[] data = baos.toByteArray();
			baos.close();
			Clip clip = (Clip) AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new ByteArrayInputStream(
					data)));
			return clip;
		} catch (Exception e) {
			return null;
		}
	}

	private void processBullets(Graphics g) {

		for (int i = 0; i < bullets.size(); i++) {
			Bullet a = bullets.get(i);
			if (!a.isRemoved()) {
				a.tick(input);
				g.fillOval((int) a.getX(), (int) a.getY(), 5, 5);
			} else {
				bullets.remove(a);
			}


			//		for(int j = 0; j < jets.size(); j++)
			//		{
			//			if(bullets.get(j).getBounds().intersects(jets.get(j).getBounds()));
			//			{
			//				g.drawImage(explosion, jets.get(i).getX(), 
			//						   jets.get(i).getY(),null);
			//				jets.remove(jets.get(j));
			//			}
			//		}
			//		
			//		for(int k = 0; k < jetsTracking.size(); k++)
			//		{
			//			if(bullets.get(k).getBounds().intersects(jetsTracking.get(k).getBounds()));
			//			{
			//				g.drawImage(explosion, jetsTracking.get(k).getX(), 
			//									   jetsTracking.get(k).getY(),null);
			//				jetsTracking.remove(jetsTracking.get(k));
			//			}
			//		}

		}
	}

	// Processes the graphics for player
	private void processPlayer(Graphics g) {
		p.tick(input);

		if (input.buttons[Input.SPACE] && !input.oldButtons[Input.SPACE]
				&& (bullets.size() < 3)) {
			incShotsFired();
			System.out.println(getShotsFired());
			bullets.add(new Bullet((int) p.getX() + 26, (int) p.getY() + 12, 1));
			input.buttons[Input.SPACE] = false;
			Clip clip = loadSound("/res/sound/Sounds/Tank Firing.wav");
			clip.start();
		}

		for (int j = 0; j < e.getEnemyBullets().size(); j++) {
			if (p.getBounds().contains(e.getEnemyBullets().get(j).getBounds())) {
				tankHit++;
				e.getEnemyBullets().remove(j);
				if (tankHit > 3) 
				{
					gameLost = true;
				}
			}
		}
		for (int j = 0; j < be.getEnemyBullets().size(); j++) {
			if (p.getBounds().contains(be.getEnemyBullets().get(j).getBounds())) {
				tankHit++;
				be.getEnemyBullets().remove(j);
				if (tankHit > 3) 
				{
					gameLost = true;
				}

				// System.out.println(tankHit);

			}
		}
		for (int i = 0; i < jets.size(); i++) {
			if(p.getBounds().intersects(jets.get(i).getBounds()))
			{
				gameLost = true;
				jets.remove(i);

			}
		}
		for (int i = 0; i < jetsTracking.size(); i++) {
			if(p.getBounds().intersects(jetsTracking.get(i).getBounds()))
			{
				gameLost = true;
				jetsTracking.remove(i);

			}

		}
		g.drawImage(p.getImage(), (int) p.getX(), (int) p.getY(), this);
	}

	private void processEnemies(Graphics g) {
		tickCount++;
		for (int i = 0; i < enemies.size(); i++) {
			e = enemies.get(i);

			tickCount++;
			if (tickCount > 200) {
				e.getEnemyBullets()
				.add(new EnemyBullet((int) e.getX(),
						(int) e.getY() + 17, 1));
				tickCount = 0;
			}
			for (int index = 0; index < e.getEnemyBullets().size(); index++) {
				EnemyBullet b = e.getEnemyBullets().get(index);
				int x = (int) b.getX() - 1;
				b.setX(x);
			}
			if (!e.isRemoved()) {
				e.tick(input);
				// g.fillRect(e.getX(), e.getY(), 50, 50);
			}
			for (int j = 0; j < bullets.size(); j++) {
				if (e.getBounds().contains(bullets.get(j).getBounds())) {
					incEnemiesHit();
					hitCount++;
					bullets.remove(j);

					if (hitCount > 4) {
						e.setAlive(false);
						e.getEnemyBullets().removeAll(e.getEnemyBullets());
						levelOne = false;
						levelTwo = true;
					}
				}
			}
		}
	}

	private void processBoss(Graphics g) {
		tickCount++;
		for (int i = 0; i < bossEnemy.size(); i++) {
			be = bossEnemy.get(i);

			tickCount++;
			if (tickCount > 350) {
				be.getEnemyBullets().add(
						new EnemyBullet((int) be.getX(), (int) be.getY() + 11,
								1));
				be.getEnemyBullets().add(
						new EnemyBullet((int) be.getX(), (int) be.getY() + 28,
								1));
				be.getEnemyBullets().add(
						new EnemyBullet((int) be.getX(), (int) be.getY() + 45,
								1));
				tickCount = 0;
			}
			for (int index = 0; index < be.getEnemyBullets().size(); index++) {
				EnemyBullet b = be.getEnemyBullets().get(index);
				int x = (int) b.getX() - 1;
				b.setX(x);
			}
			if (!be.isRemoved()) {
				be.tick(input);
				// g.fillRect(e.getX(), e.getY(), 50, 50);
			}
			for (int j = 0; j < bullets.size(); j++) {
				if (be.getBounds().contains((bullets.get(j).getBounds()))) {
					incEnemiesHit();
					bossHitCount++;
					bullets.remove(j);
					// System.out.println(bossHitCount); for debugging
					if (bossHitCount > 15) {
						bossEnemy.remove(be);
						be.getEnemyBullets().removeAll(be.getEnemyBullets());
						int count = 1;
						//						Input.endGameMusic();
						gameWon  = true;

					}
				}
			}
		}
	}

	private void processBackground(Graphics g) {

		scrollTick += 0.5;
		jetTickA++;
		jetTickB++;

		if (scrollTick > 1) {
			backgroundScrollX--;
			g.drawImage(background, backgroundScrollX, 0, null);
			scrollTick = 0.0;
			if (backgroundScrollX <= -1000) {
				stopScrolling = true;
				levelTwo = false;
				levelThree = true;
			}
		}

	}

	private void processJets(Graphics g) {

		int jetCountA = 0;
		int jetCountB = 0;
		// System.out.println(jetTickA);
		if (jetTickA == 175.0) {
			jets.add(new Jet(500, jSpawn.nextInt(420), jet, 1));
			jetCountA++;
			jetTickA = 0;
		}

		if (jetTickB == 225.0) {
			jetsTracking.add(new Jet(500, jSpawn.nextInt(420), jet, 1));
			jetCountB++;
			jetTickB = 0;
		}
		for (int i = 0; i < jets.size(); i++) {
			// count++;
			jA = jets.get(i);
			// System.out.println("Spawn Jet");
			// System.out.println(count);

			for (int j = 0; j < bullets.size(); j++) {
				if (jets.get(i).getBounds()
						.contains(bullets.get(j).getBounds())) {
					incEnemiesHit();
					jets.get(i).setAlive(false);
					bullets.remove(j);
				}
			}
		}

		for (int i = 0; i < jetsTracking.size(); i++) {
			// count++;
			jT = jetsTracking.get(i);
			// System.out.println("Spawn Jet");
			// System.out.println(count);

			for (int j = 0; j < bullets.size(); j++) {
				if (jetsTracking.get(i).getBounds()
						.contains(bullets.get(j).getBounds())) {
					incEnemiesHit();
					jetsTracking.get(i).setAlive(false);
					bullets.remove(j);
				}
			}
		}
	}

	private void renderGame(Graphics g) {

		// g.fillRect(0, 0, getWidth(), getHeight());

		if (levelOne) {
			g.drawImage(background, 0, 0, null);
		}

		else if (levelTwo) {
			processBackground(g);
			processJets(g);
		}

		else if (levelThree) {
			g.drawImage(background, 0, 0, 500, 500, 1000, 0, 1500, 500, null);
			// System.out.println("level 3");
		}

		e.tick(input);

		if (enemies.size() >= 1) {
			g.drawImage(turret, (int) e.getX(), (int) e.getY(), null);
			if (enemies.get(0).getAlive() == false) {
				enemies.remove(e);

			}
		}

		if (jets.size() >= 1) {

			for (int i = 0; i < jets.size(); i++) {
				g.drawImage(jet, (int) jets.get(i).getX(), (int) jets.get(i)
						.getY(), null);
				jets.get(i).tick(input);

				if (jets.get(i).getAlive() == false) {
					g.drawImage(explosion, jets.get(i).getX(), 
							jets.get(i).getY(),null);
					jets.remove(jets.get(i));
				}

			}
		}

		if (jetsTracking.size() >= 1) {

			for (int i = 0; i < jetsTracking.size(); i++) {
				g.drawImage(jet, (int) jetsTracking.get(i).getX(),
						(int) jetsTracking.get(i).getY(), null);
				jetsTracking.get(i).tick(input);

				if (jetsTracking.get(i).getAlive() == false) {
					g.drawImage(explosion, jetsTracking.get(i).getX(), 
							jetsTracking.get(i).getY(),null);
					jetsTracking.remove(jetsTracking.get(i));
				}
			}
		}

		if (bossEnemy.size() >= 1 && levelThree == true) {
			g.drawImage(boss, (int) be.getX(), (int) be.getY(), null);
			processBoss(g);
			be.bossDrawBullets(g);
		}

		e.drawBullets(g);
		processPlayer(g);
		processBullets(g);		
		processEnemies(g);
		// g.drawString("Sample Game", 400, 50);
	}

	// the main game loop
	private void render() {

		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			requestFocus();
			return;
		}
		Graphics g = bs.getDrawGraphics();

		if (newRun) {
			input.isMenuOn = true;
			menu.render(g);

		} else if (input.isMenuOn) {
			input.isControlsOn = false;
			menu.render(g);
		} else if (input.isControlsOn) {
			input.isMenuOn = false;
			controls.render(g);
		} else if (gameWon){
			winGame.render(g);
		} else if (gameLost){
			gameOver.render(g);
		} else
		{
			renderGame(g);
		}

		g.dispose();
		bs.show();

		// game is too fast without this delay
		try {
			Thread.sleep(6);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			render();
		}
	}

	public void start() {
		new Thread(this).start();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		input.set(arg0.getKeyCode(), false);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_F1) {
			input.setMenu();
			setNewRun();
		} else if (e.getKeyCode() == KeyEvent.VK_F2) {
			input.isMenuOn = false;
			input.setControls();
			setNewRun();
		}
		input.set(e.getKeyCode(), true);
	}

}