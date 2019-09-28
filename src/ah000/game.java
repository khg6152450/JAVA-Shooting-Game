package ah000;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class game {

	public static void main(String[] arg) {

		@SuppressWarnings("unused")
		game_Frame fms = new game_Frame();

	}

}

@SuppressWarnings("serial")
class game_Frame extends JFrame implements KeyListener, Runnable {

	/*
	 * 
	 * 癰귨옙占쎈땾占쎄퐨占쎈섧�겫占썽겫占�
	 * 
	 */
	int game_mode;
	final static int GAME_LOAD = 0; // 野껊슣�뿫占쎈퓠占쎄퐣 占쎄텢占쎌뒠占쎈┷占쎈뮉 占쎌뵠沃섎챷占�, 占쎄텢占쎌뒲占쎈굡 嚥≪뮆逾�
	final static int GAME_INIT = 1; // 野껊슣�뿫 �룯�뜃由곤옙�넅
	final static int GAME_PLAY = 2; // 野껊슣�뿫 占쎈탣占쎌쟿占쎌뵠
	final static int GAME_CLEAR = 3; // 野껊슣�뿫 占쎄깻�뵳�딅선
	final static int GAME_OVER = 4; // 野껊슣�뿫 占쎌궎甕곤옙

	int f_width; // 占쎈늄占쎌쟿占쎌뿫占쎄섶占쎌뵠揶쏅�れ뱽獄쏆룆�뮉癰귨옙占쎈땾

	int f_height; // 占쎈늄占쎌쟿占쎌뿫占쎈꼥占쎌뵠揶쏅�れ뱽獄쏆룆�뮉癰귨옙占쎈땾

	static int x, y; // 占쎈탣占쎌쟿占쎌뵠占쎈선筌�癒��봼占쎄숲占쎌벥占쎌겱占쎌삺�넫�슦紐닷첎誘れ뱽獄쏆룇�뱽癰귨옙占쎈땾

	int[] cx = { 0, 0, 0 }; // 獄쏄퀗瑗랃옙�뮞占쎄쾿嚥▲끉�꺗占쎈즲占쎌젫占쎈선占쎌뒠癰귨옙占쎈땾

	int bx = -1000; // 占쎌읈筌ｋ�媛쇿칰�럩�뮞占쎄쾿嚥▲끉�뒠癰귨옙占쎈땾

	boolean KeyUp = false; // 占쎄텕癰귣�諭띰옙沅롥첎誘れ뱽獄쏆룇�뱽癰귨옙占쎈땾

	boolean KeyDown = false;

	boolean KeyLeft = false;

	boolean KeyRight = false;

	boolean KeySpace = false;

	boolean waiting_play = true; // 占쎈뻻占쎌삂占쎈릭疫꿸퀡占쏙옙 疫꿸퀡�뼄�뵳�됰뮉 flag\

	boolean game_start = true;

	int cnt; // �눧�똾釉녕뙴�뫂遊쏙옙�뒒占쎈땾�몴�눘萸낉옙�뒲占쎈뱜占쎈막癰귨옙占쎈땾

	int player_Speed;// 占쎌�占쏙옙占쎌벥筌�癒��봼占쎄숲揶쏉옙占쏙옙筌욊낯�뵠占쎈뮉占쎈꺗占쎈즲�몴�눘�쒙옙�쟿占쎈막癰귨옙占쎈땾

	int missile_Speed; // 沃섎챷沅쀯옙�뵬占쎌뵠占쎄텊占쎌뵬揶쏉옙占쎈뮉占쎈꺗占쎈즲鈺곌퀣�쟿占쎈막癰귨옙占쎈땾

	int fire_Speed; // 沃섎챷沅쀯옙�뵬占쎈염占쎄텢占쎈꺗占쎈즲鈺곌퀣�쟿癰귨옙占쎈땾

	int enemy_speed; // 占쎌읅占쎌뵠占쎈짗占쎈꺗占쎈즲占쎄퐬占쎌젟

	int player_Status = 0;

	int creature_Status = 3; // 1. fire 2. frozen 3. electronic

	// 占쎌�占쏙옙筌�癒��봼占쎄숲占쎄맒占쎄묶筌ｋ똾寃뺠퉪占쏙옙�땾0 : 占쎈즸占쎄맒占쎈뻻, 1: 沃섎챷沅쀯옙�뵬獄쏆뮇沅�, 2: �빊�뫖猷�

	static int game_Score; // 野껊슣�뿫占쎌젎占쎈땾�④쑴沅�

	static int player_Hitpoint; // 占쎈탣占쎌쟿占쎌뵠占쎈선筌�癒��봼占쎄숲占쎌벥筌ｋ��젾

	Thread th; // 占쎈뮞占쎌쟿占쎈굡占쎄문占쎄쉐

	Image[] Player_img;

	// 占쎈탣占쎌쟿占쎌뵠占쎈선占쎈막占쎈빍筌롫뗄�뵠占쎈�∽옙紐댐옙�겱占쎌뱽占쎌맄占쎈퉸占쎌뵠沃섎챷占썹몴�눖媛숋옙肉닸에�뮆而븝옙�벉

	Image BackGround_img; // 獄쏄퀗瑗랃옙�넅筌롫똻�뵠沃섎챷占�

	Image[] Cloud_img; // 占쏙옙筌욊낯�뵠占쎈뮉獄쏄퀗瑗랃옙�뒠占쎌뵠沃섎챷占썼쳸怨쀫였

	Image[] Explo_img; // 占쎈：獄쏆뮇�뵠占쎈읃占쎈뱜占쎌뒠占쎌뵠沃섎챷占썼쳸怨쀫였

	Image Missile_img; // 占쎈탣占쎌쟿占쎌뵠占쎈선沃섎챷沅쀯옙�뵬占쎌뵠沃섎챷占쏙옙源�占쎄쉐

	Image Enemy_img; // 占쎌읅占쎌뵠沃섎챷占쏙옙源�占쎄쉐

	Image Missile2_img;// 占쎌읅沃섎챷沅쀯옙�뵬占쎌뵠沃섎챷占썹빊遺쏙옙占쎄문占쎄쉐

	Image Missile3_img;// �겫�뜆�꺗占쎄쉐占쎄쾿�뵳�딆퓗沃섎챷沅쀯옙�뵬占쎌뵠沃섎챷占썹빊遺쏙옙占쎄문占쎄쉐

	Image Missile4_img;// 占쎈섰占쎌벉占쎈꺗占쎄쉐占쎄쾿�뵳�딇�쒑첋紐꾧텢占쎌뵬占쎌뵠沃섎챷占썹빊遺쏙옙占쎄문占쎄쉐

	Image Missile5_img;// 甕곕뜃而삼옙�꺗占쎄쉐占쎄쾿�뵳�딇�쒑첋紐꾧텢占쎌뵬占쎌뵠沃섎챷占썹빊遺쏙옙占쎄문占쎄쉐

	Image[] Creature_img;// 占쎄쾿�뵳�딆퓗占쎌뵠沃섎챷占썹빊遺쏙옙占쎄문占쎄쉐

	ArrayList<Missile> Missile_List = new ArrayList<Missile>();

	// 占쎈뼄占쎈땾占쎌벥沃섎챷沅쀯옙�뵬占쎌뱽�꽴占썹뵳�뗫릭疫꿸퀣�맄占쎈립獄쏄퀣肉�

	ArrayList<Enemy> Enemy_List = new ArrayList<Enemy>();

	// 占쎈뼄占쎈땾占쎌벥占쎌읅占쎌뱽�꽴占썹뵳�뗫릭疫꿸퀣�맄占쎈립獄쏄퀣肉�

	ArrayList<Enemy> Enemy_List2 = new ArrayList<Enemy>();

	// 占쎈뼄占쎈땾占쎌벥占쎌읅占쎌뱽�꽴占썹뵳�뗫릭疫꿸퀣�맄占쎈립獄쏄퀣肉�

	ArrayList<Explosion> Explosion_List = new ArrayList<Explosion>();

	// 占쎈뼄占쎈땾占쎌벥占쎈：獄쏆뮇�뵠占쎈읃占쎈뱜�몴�눘荑귞뵳�뗫릭疫꿸퀣�맄占쎈립獄쏄퀣肉�

	Image buffImage; // 占쎈쐭�뇡遺얠쒔占쎈쓠筌띻낯�뱽占쎌맄占쎈립甕곌쑵�쓠

	Graphics buffg; // 占쎈쐭�뇡遺얠쒔占쎈쓠筌띻낯�뱽占쎌맄占쎈립甕곌쑵�쓠

	Missile ms; // 沃섎챷沅쀯옙�뵬占쎄깻占쎌삋占쎈뮞占쎌젔域뱀눛沅�

	Enemy en; // 占쎈퓠占쎄섐沃섎챸寃�占쎌삋占쎈뮞占쎌젔域뱀눛沅�

	Enemy en2; // 占쎈퓠占쎄섐沃섓옙 占쎌젔域뱀눛沅� 2

	Explosion ex; // 占쎈：獄쏆뮇�뵠占쎈읃占쎈뱜占쎌뒠占쎄깻占쎌삋占쎈뮞占쎌젔域뱀눛沅�

	/*
	 * 
	 * 占쎈늄占쎌쟿占쎌뿫占쎄문占쎄쉐�겫占썽겫占�
	 * 
	 */

	game_Frame() {// 占쎌넅筌롫똻肉됭퉪�똻肉э쭪�뜇遊쏙옙�쟿占쎌뿫占쎄문占쎄쉐筌롫뗄�꺖占쎈굡

		init();

		start();

		setTitle("占쎈뭼占쎈샒野껊슣�뿫筌띾슢諭얏묾占�");// 占쎈늄占쎌쟿占쎌뿫占쏙옙占쎌뵠占쏙옙占쎄퐬占쎌젟

		setSize(f_width, f_height);// 占쎈늄占쎌쟿占쎌뿫占쎄쾿疫꿸퀣苑뺧옙�젟

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		// 筌뤴뫀�빍占쎄숲占쎌넅筌롫똾鍮먲옙湲쏙옙猷꾬옙寃뺞묾怨뚯빽獄쏆룇釉섓옙諭억옙�뵠疫뀐옙

		int f_xpos = (int) (screen.getWidth() / 2 - f_width / 2);

		int f_ypos = (int) (screen.getHeight() / 2 - f_height / 2);

		// 占쎈늄占쎌쟿占쎌뿫占쎌뱽筌뤴뫀�빍占쎄숲占쎌넅筌롫똻�젟餓λ쵐釉곤옙肉됭쳸怨쀭뒄占쎈릭疫꿸퀣�맄占쎈립�넫�슦紐닷첎誘⑦�롳옙沅�

		setLocation(f_xpos, f_ypos); // 占쎈늄占쎌쟿占쎌뿫占쎌뱽占쎈퉸占쎈뼣占쎌맄燁살꼷肉됭쳸怨쀭뒄

		setResizable(false); // 占쎈늄占쎌쟿占쎌뿫占쎄쾿疫꿸퀡占쎌눘�뿫占쎌벥嚥≪뮆占썲칰��釉�占쎈뮉野껉퍓媛묕쭪占�

		setVisible(true);// 占쎈늄占쎌쟿占쎌뿫占쎌뱽占쎌넅筌롫똻肉됭퉪�똻�뵠野껊슢彛뷂옙諭븝옙�뼄

	}

	/*
	 * 
	 * 疫꿸퀡�궚占쎄퐬占쎌젟�겫占썽겫占�
	 * 
	 */

	public void init() { // 疫꿸퀡�궚占쎌읅占쎌뵥野껊슣�뿫占쎄퐬占쎌젟占쎌뱽�꽴占썹뵳�뗫막筌롫뗄�꺖占쎈굡

		x = 220; // 筌ㅼ뮇�겧占쎈탣占쎌쟿占쎌뵠占쎈선占쎈뻻占쎌삂x�넫�슦紐�

		y = 800; // 筌ㅼ뮇�겧占쎈탣占쎌쟿占쎌뵠占쎈선占쎈뻻占쎌삂y�넫�슦紐�

		f_width = 500; // 占쎈늄占쎌쟿占쎌뿫占쎄섶占쎌뵠揶쏅�り퐬占쎌젟

		f_height = 900; // 占쎈늄占쎌쟿占쎌뿫占쎈꼥占쎌뵠揶쏅�り퐬占쎌젟

		Missile_img = new ImageIcon("Missile.png").getImage();

		// 占쎈탣占쎌쟿占쎌뵠占쎈선沃섎챷沅쀯옙�뵬占쎌뵠沃섎챷占쏙옙�솁占쎌뵬獄쏆룇釉섓옙諭억옙�뵠疫뀐옙

		Missile2_img = new ImageIcon("Missile2.png").getImage();

		// 占쎌읅沃섎챷沅쀯옙�뵬占쎌뵠沃섎챷占쏙옙�솁占쎌뵬獄쏆룇釉섓옙諭억옙�뵠疫뀐옙

		Missile3_img = new ImageIcon("Missile3.png").getImage();

		Missile4_img = new ImageIcon("Missile4.png").getImage();

		Missile5_img = new ImageIcon("Missile5.png").getImage();

		Enemy_img = new ImageIcon("Enemy_0.png").getImage();

		// 占쎌읅占쎌뵠沃섎챷占쏙옙�솁占쎌뵬獄쏆룇釉섓옙諭억옙�뵠疫뀐옙

		Creature_img = new Image[7];

		for (int i = 0; i < Creature_img.length; ++i) {
			Creature_img[i] = new ImageIcon("Creature_" + i + ".png").getImage();
		}

		// 占쎄쾿�뵳�딆퓗占쎌뵠沃섎챷占� 獄쏆룇釉섓옙諭억옙�뵠疫뀐옙

		Player_img = new Image[5];

		Player_img[0] = new ImageIcon("man_0.png").getImage();

		for (int i = 0; i < Player_img.length; ++i) {

			Player_img[i] = new ImageIcon("man_" + i + ".png").getImage();

		}

		// 占쎈탣占쎌쟿占쎌뵠占쎈선占쎈막占쎈빍筌롫뗄�뵠占쎈�∽옙紐댐옙�겱占쎌뱽占쎌맄占쎈퉸占쎈솁占쎌뵬占쎌뵠�뵳袁⑹뱽

		// 占쎄퐜甕곌쑬彛륅옙�뼄占쎄돌占쎈떊獄쏄퀣肉닸에�뮆�뼖占쎈뮉占쎈뼄.

		BackGround_img = new ImageIcon("background.png").getImage();

		// 占쎌읈筌ｋ�媛쇿칰���넅筌롫똻�뵠沃섎챷占썹몴�눖而븝옙�뮸占쎈빍占쎈뼄.

		Cloud_img = new Image[3];

		for (int i = 0; i < Cloud_img.length; ++i) {

			Cloud_img[i] = new ImageIcon("cloud_" + i + ".png").getImage();

		}

		// �뤃�됱カ占쎌뱽3揶쏆뮆猷욑옙�뻻占쎈퓠域밸챶�봺占쎈뮉占쎈쑓占쎈젶占쎌벥占쎄맒獄쏄퀣肉닸에占�3揶쏆뮆占쎌눖猷욑옙�뻻占쎈퓠獄쏆룆�뮉占쎈뼄.

		Explo_img = new Image[3];

		for (int i = 0; i < Explo_img.length; ++i) {

			Explo_img[i] = new ImageIcon("explo_" + i + ".png").getImage();

		}

		// 占쎈：獄쏆뮇釉뤄옙�빍筌롫뗄�뵠占쎈�∽옙紐댐옙�겱占쎌뱽占쎌맄占쎈퉸

		// 占쎈솁占쎌뵬占쎌뵠�뵳袁⑹뱽占쎄퐜甕곌쑬彛륅옙�뼄占쎄돌占쎈떊獄쏄퀣肉닸에�뮆�뼖占쎈뮉占쎈뼄.

		// 筌뤴뫀諭븝옙�뵠沃섎챷占쏙옙�뮉Swing占쎌벥 ImageIcon占쎌몵嚥≪뮆而븝옙釉섓옙�뵠沃섎챷占쏙옙瑗삼옙�뵠,占쎈꼥占쎌뵠// 揶쏅�れ뱽獄쏅뗀以덌옙堉몌옙�뱽占쎈땾占쎌뿳野껊슦釉놂옙�뼄.

		game_Score = 0;// 野껊슣�뿫占쎈뮞�굜遺용선�룯�뜃由곤옙�넅

		player_Hitpoint = 3;// 筌ㅼ뮇�겧占쎈탣占쎌쟿占쎌뵠占쎈선筌ｋ��젾

		player_Speed = 7; // 占쎌�占쏙옙筌�癒��봼占쎄숲占쏙옙筌욊낯�뵠占쎈뮉占쎈꺗占쎈즲占쎄퐬占쎌젟

		missile_Speed = 11; // 沃섎챷沅쀯옙�뵬占쏙옙筌욊낯�뿫占쎈꺗占쎈즲占쎄퐬占쎌젟

		fire_Speed = 15; // 沃섎챷沅쀯옙�뵬占쎈염占쎄텢占쎈꺗占쎈즲占쎄퐬占쎌젟

		enemy_speed = 3;// 占쎌읅占쎌뵠占쎄텊占쎌뵬占쎌궎占쎈뮉占쎈꺗占쎈즲占쎄퐬占쎌젟

		game_mode = GAME_PLAY;

	}

	public void start() { // 疫꿸퀡�궚占쎌읅占쎌몵嚥≪뮇�뼄占쎈뻬占쎈릭占쎈뮉筌롫뗄�꺖占쎈굡

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 占쎈늄占쎌쟿占쎌뿫占쎌궎�몴紐꾠걹占쎌맄X 甕곌쑵�뱣占쎌뱽占쎄깻�뵳占쏙옙�뻻占쎈늄嚥≪뮄�젃占쎌삪�넫�굝利�

		addKeyListener(this);

		// 占쎄텕癰귣�諭띰옙�뿯占쎌젾筌ｌ꼶�봺獄쏆룇釉섓옙諭억옙�뵠疫뀐옙

		th = new Thread(this);

		// 占쎄퉱嚥≪뮇�뒲占쎈뮞占쎌쟿占쎈굡�몴�눘源�占쎄쉐

		th.start();

		// 占쎈뮞占쎌쟿占쎈굡占쎈뻻占쎌삂

	}

	/*
	 * 
	 * 占쎈뮞占쎌쟿占쎈굡
	 * 
	 */

	public void run() {// 占쎈뮞占쎌쟿占쎈굡占쎈뼄占쎈뻬�겫占썽겫占�

		try {// 占쎈뮞占쎌쟿占쎈굡嚥≪뮇�뵥占쎈립占쎈퓠占쎌쑎獄쎻뫗占쏙옙�뒠占쎌굙占쎌뇚筌ｌ꼶�봺

			while (waiting_play) { // �눧�똾釉녕뙴�뫂遊쏙옙猷롧뵳�덈┛

				KeyProcess();

				EnemyProcess();

				EnemyProcess2();

				MissileProcess();

				ExplosionProcess();

				// 揶쏄낯伊뚳㎗�꼶�봺筌롫뗄�꺖占쎈굡占쎈뼄占쎈뻬

				repaint();

				// 域밸챶�삋占쎈동筌ｌ꼷�벉�겫占쏙옙苑ｏ옙�뼄占쎈뻻域밸챶�봺疫뀐옙

				Thread.sleep(20);

				// 20milli sec 占쎈짗占쎈툧占쎈뮞占쎌쟿占쎈굡占쎈뮩�뵳占�

				cnt++;

				// �눧�똾釉녕뙴�뫂遊쏙옙猷롳옙釉ｏ쭕�뜄�뼄cnt +1 燁삳똻�뒲占쎈뱜
				if (player_Hitpoint < 0) {
					 game_mode = 4; // 野껊슣�뿫占쎌궎甕곤옙
				} else
					game_mode = 2;

			}

		} catch (Exception e) {

		}

	}

	public void clearScreen() {
		buffg.setColor(Color.white);
		buffg.fillRect(0, 0, 500, 909);
	}

	public void gameOver() {

		clearScreen();

		buffg.setFont(new Font("TimesRaman", Font.BOLD, 20));
		buffg.setColor(Color.BLACK);
		buffg.drawString("GAME OVER", 190, f_height / 2);
		buffg.setFont(new Font("TimesRaman", Font.BOLD, 20));
		buffg.drawString("Game Score :  " + game_Score, 170, f_height / 2 + 50);
		buffg.setFont(new Font("TimesRaman", Font.BOLD, 20));
		buffg.drawString("Press any key to start!", 145, f_height / 2 + 100);

	}

	/*
	 * 
	 * 筌롫뗄�뵥筌ｌ꼶�봺筌롫뗄�꺖占쎈굡
	 * 
	 */

	public void MissileProcess() {// 沃섎챷沅쀯옙�뵬�꽴占쏙옙�졃筌ｌ꼶�봺筌롫뗄�꺖占쎈굡

		if (KeySpace) { // 占쎄텕癰귣�諭띰옙�뮞占쎈읂占쎌뵠占쎈뮞占쎄텕占쎌뿯占쎌젾占쎈연�겫占쏙옙�넇占쎌뵥

			player_Status = 1; // 占쎈탣占쎌쟿占쎌뵠占쎈선占쎄맒占쎄묶�몴占�1嚥≪뮆占썲칰占�

			if ((cnt % fire_Speed) == 0) {

				// �뙴�뫂遊썹㎉�똻�뒲占쎄숲占쎌벥揶쏅�る퓠占쎄퐣fire_Speed占쎈퓠占쎄퐣占쎄퐬占쎌젟占쎈립揶쏅�⑹춸占쎄껍

				// 占쎄돌占쎈듌揶쏅�れ벥占쎄돌�솒紐꾬옙揶쏉옙0占쎌뵥筌욑옙占쎈연�겫占썹몴�눛�넇占쎌뵥

				ms = new Missile(x + 17, y, 270, missile_Speed, 0);

				// 疫꿸퀡�궚占쎌읅占쎌뵥 占쎌맄筌잞옙 筌욊낱六� 沃섎챷沅쀯옙�뵬

				// 揶쏄낮猷꾢첎誘わ옙 270 占쎌뵠疫꿸퀡�궚揶쏅�れ뿯占쎈빍占쎈뼄.

				// 占쎌뇢筌잛럥占쏙옙苑ｈ첋紐꾧텢占쎌뵬x�넫�슦紐�, y�넫�슦紐�, 沃섎챷沅쀯옙�뵬筌욊쑵六얕쳸�뫚堉�, 沃섎챷沅쀯옙�뵬占쎈꺗占쎈즲, 沃섎챷沅쀯옙�뵬�넫�굝履�

				// 沃섎챷沅쀯옙�뵬�넫�굝履�0 : 占쎈탣占쎌쟿占쎌뵠占쎈선揶쏉옙獄쏆뮇沅쀯옙釉�占쎈뮉沃섎챷沅쀯옙�뵬, 1 : 占쎌읅占쎌뵠獄쏆뮇沅쀯옙釉�占쎈뮉沃섎챷沅쀯옙�뵬

				Missile_List.add(ms);

				// 沃섎챷沅쀯옙�뵬獄쏄퀣肉댐옙肉됵옙鍮먲옙�뼣占쎌맄燁살꼷�벥沃섎챷沅쀯옙�뵬�빊遺쏙옙

				ms = new Missile(x + 17, y, 315, missile_Speed, 0);

				// 占쎌궎�몴紐꾠걹占쏙옙揶쏄낯苑묕옙�몵嚥≪뮆沅뉛옙�뵬揶쏅뜄占쎈챷沅쀯옙�뵬占쎌뿯占쎈빍占쎈뼄.

				Missile_List.add(ms);

				// 沃섎챷沅쀯옙�뵬獄쏄퀣肉댐옙肉됵옙鍮먲옙�뼣占쎌맄燁살꼷�벥沃섎챷沅쀯옙�뵬�빊遺쏙옙

				ms = new Missile(x + 17, y, 225, missile_Speed, 0);

				// 占쎌뇢筌잛럥占썲첎怨멸퐨占쎌몵嚥≪뮆沅뉛옙�뵬揶쏅뜄占쎈챷沅쀯옙�뵬占쎌뿯占쎈빍占쎈뼄.

				Missile_List.add(ms);

				// 沃섎챷沅쀯옙�뵬獄쏄퀣肉댐옙肉됵옙鍮먲옙�뼣占쎌맄燁살꼷�벥沃섎챷沅쀯옙�뵬�빊遺쏙옙
			}

			if (((cnt % (5 * fire_Speed)) == 0) && creature_Status == 1) {

				ms = new Missile(x + 30, y - 25, 270, 2 * missile_Speed, 2);

				// 占쎄쾿�뵳�딆퓗 沃섎챷沅쀯옙�뵬 占쎌맄

				Missile_List.add(ms);

			}
			if (((cnt % (5 * fire_Speed)) == 0) && creature_Status == 2) {

				ms = new Missile(x + 30, y - 25, 270, 2 * missile_Speed, 3);

				// 占쎄쾿�뵳�딆퓗 沃섎챷沅쀯옙�뵬 占쎌맄

				Missile_List.add(ms);

			}
			if (((cnt % (5 * fire_Speed)) == 0) && creature_Status == 3) {

				ms = new Missile(x + 30, y - 25, 270, 5 * missile_Speed, 4);

				// 占쎄쾿�뵳�딆퓗 沃섎챷沅쀯옙�뵬 占쎌맄

				Missile_List.add(ms);

			}
			

		}

		for (int i = 0; i < Missile_List.size(); ++i) {

			// 沃섎챷沅쀯옙�뵬獄쏄퀣肉댐옙肉됬빊遺쏙옙占쎈쭆沃섎챷沅쀯옙�뵬占쎌뵠占쎈섰筌띾뜄援뱄옙�뿳占쎈뮉筌욑옙占쎌넇占쎌뵥

			ms = (Missile) Missile_List.get(i);

			// 獄쏄퀣肉댐옙肉됭�곕똻�삺占쎈릭占쎈뮉沃섎챷沅쀯옙�뵬占쎌벥揶쏆빘猿쒐몴�눖而븝옙釉섓옙�궔占쎈뼄.

			ms.move();

			// 占쎈퉸占쎈뼣沃섎챷沅쀯옙�뵬占쎌뱽占쏙옙筌욊낯�뵠野껊슢彛뷂옙諭븝옙�뼄

			if (ms.x > f_width - 20 || ms.x < 0 || ms.y < 0 || ms.y > f_height) {

				// 占쎈퉸占쎈뼣沃섎챷沅쀯옙�뵬占쎌뵠占쎌넅筌롫�而곤옙�몵嚥≪뮆援밧첎遺얜뮉揶쏉옙占쎈연�겫占썹몴�눛�넇占쎌뵥

				Missile_List.remove(i);

				// 占쎌넅筌롫�嫄방틦�슣占쏙옙猷꾬옙�뼎占쎈립沃섎챷沅쀯옙�뵬占쎄텣占쎌젫

			}

			if (Crash(x, y, ms.x, ms.y, Player_img[0], Missile2_img) && ms.who == 1) {

				// 占쎌읅占쎌뵠獄쏆뮇沅쀯옙釉녘첋紐꾧텢占쎌뵬占쎌뵠占쎈탣占쎌쟿占쎌뵠占쎈선占쏙옙�빊�뫖猷롳옙釉�占쎈뮉筌욑옙占쎈연�겫占썹몴�눛�넇占쎌뵥

				player_Hitpoint--;

				// 占쎈탣占쎌쟿占쎌뵠占쎈선筌ｋ��젾占쎈７占쎌뵥占쎈뱜�몴占�1占쎄텣揶쏉옙

				ex = new Explosion(x - 50, y, 1);

				// 占쎈탣占쎌쟿占쎌뵠占쎈선占쎌쁽�뵳�딅퓠�빊�뫖猷롳옙�뒠占쎈：獄쏆뮇�뵠占쎈읃占쎈뱜揶쏆빘猿쒙옙源�占쎄쉐

				Explosion_List.add(ex);

				// 占쎄문占쎄쉐占쎈립揶쏆빘猿쒐몴�눖媛숋옙肉닸에�뮇占쏙옙�삢

				Missile_List.remove(i);

				// 占쎈퉸占쎈뼣占쎈┷占쎈뮉占쎌읅沃섎챷沅쀯옙�뵬占쎄텣占쎌젫

			}

			for (int j = 0; j < Enemy_List.size(); ++j) {

				// 占쎈퓠占쎄섐沃섎챶媛숋옙肉댐옙肉됬빊遺쏙옙占쎈쭆占쎌읅占쎌뵠占쎈섰筌띾뜄援뱄옙�뿳占쎈뮉筌욑옙占쎌넇占쎌뵥

				en = (Enemy) Enemy_List.get(j);

				// 獄쏄퀣肉댐옙肉됭�곕똻�삺占쎈릭占쎈뮉占쎌읅占쎌벥揶쏆빘猿쒐몴�눖而븝옙釉섓옙�궔占쎈뼄

				if (Crash(ms.x, ms.y, en.x, en.y, Missile_img, Enemy_img) && (ms.who == 0)) {

					// 占쎈탣占쎌쟿占쎌뵠占쎈선沃섎챷沅쀯옙�뵬�⑥눘�읅�빊�뫖猷롳옙�솇占쎌젟

					// 沃섎챷沅쀯옙�뵬占쎌벥�넫�슦紐닺쳸瑜곸뵠沃섎챷占쏙옙�솁占쎌뵬,

					// 占쎌읅占쎌벥�넫�슦紐닺쳸瑜곸뵠沃섎챷占쏙옙�솁占쎌뵬占쎌뱽獄쏆룇釉�

					// �빊�뫖猷롳옙�솇占쎌젟筌롫뗄�꺖占쎈굡嚥≪뮆苑녷묾怨뚰�촷rue,false揶쏅�れ뱽

					// �뵳�뗪쉘獄쏆룇釉쁳rue筌롳옙 占쎈툡占쎌삋�몴�눘�뼄占쎈뻬占쎈�占쎈빍占쎈뼄.

					en.Hit_point--;

					Missile_List.remove(i);

					if (en.Hit_point < 0) {
						Enemy_List.remove(j);
					}
					// �빊�뫖猷롳옙釉놂옙�읅占쎄텣占쎌젫

					game_Score += 10; // 野껊슣�뿫占쎌젎占쎈땾�몴占�+10占쎌젎.

					ex = new Explosion(en.x + Enemy_img.getWidth(null) / 2,

							en.y + Enemy_img.getHeight(null) / 2, 0);

					// 占쎌읅占쎌뵠占쎌맄燁살꼹鍮먲옙�뿳占쎈뮉�⑤끃�벥餓λ쵐�뼎�넫�슦紐큫,y 揶쏅�⑤궢

					// 占쎈：獄쏆뮇苑뺧옙�젟占쎌뱽獄쏆룇占썲첎占�( 0 占쎌굢占쎈뮉1 )占쎌뱽獄쏆룇�뮸占쎈빍占쎈뼄.

					// 占쎈：獄쏆뮇苑뺧옙�젟揶쏉옙- 0 : 占쎈：獄쏉옙, 1 : 占쎈뼊占쎈떄占쎈돗野껓옙

					Explosion_List.add(ex);

					// �빊�뫖猷롳옙�솇占쎌젟占쎌몵嚥≪뮇沅쀯옙�뵬筌욊쑴�읅占쎌벥占쎌맄燁살꼷肉�

					// 占쎌뵠占쎈읃占쎈뱜�몴�눘�뀤揶쏉옙占쎈립占쎈뼄.

				}

				if (Crash(ms.x, ms.y, en.x, en.y, Missile2_img, Enemy_img) && (ms.who == 2)) {

					// 占쎈탣占쎌쟿占쎌뵠占쎈선沃섎챷沅쀯옙�뵬�⑥눘�읅�빊�뫖猷롳옙�솇占쎌젟

					// 沃섎챷沅쀯옙�뵬占쎌벥�넫�슦紐닺쳸瑜곸뵠沃섎챷占쏙옙�솁占쎌뵬,

					// 占쎌읅占쎌벥�넫�슦紐닺쳸瑜곸뵠沃섎챷占쏙옙�솁占쎌뵬占쎌뱽獄쏆룇釉�

					// �빊�뫖猷롳옙�솇占쎌젟筌롫뗄�꺖占쎈굡嚥≪뮆苑녷묾怨뚰�촷rue,false揶쏅�れ뱽

					// �뵳�뗪쉘獄쏆룇釉쁳rue筌롳옙 占쎈툡占쎌삋�몴�눘�뼄占쎈뻬占쎈�占쎈빍占쎈뼄.

					en.Hit_point--;
					en.Hit_point--;
					en.Hit_point--;
					en.Hit_point--;
					en.Hit_point--;
					en.Hit_point--;

					Missile_List.remove(i);

					if (en.Hit_point < 0) {
						Enemy_List.remove(j);
					}
					// �빊�뫖猷롳옙釉놂옙�읅占쎄텣占쎌젫

					game_Score += 60; // 野껊슣�뿫占쎌젎占쎈땾�몴占�+10占쎌젎.

					ex = new Explosion(en.x + Enemy_img.getWidth(null) / 2,

							en.y + Enemy_img.getHeight(null) / 2, 0);

					// 占쎌읅占쎌뵠占쎌맄燁살꼹鍮먲옙�뿳占쎈뮉�⑤끃�벥餓λ쵐�뼎�넫�슦紐큫,y 揶쏅�⑤궢

					// 占쎈：獄쏆뮇苑뺧옙�젟占쎌뱽獄쏆룇占썲첎占�( 0 占쎌굢占쎈뮉1 )占쎌뱽獄쏆룇�뮸占쎈빍占쎈뼄.

					// 占쎈：獄쏆뮇苑뺧옙�젟揶쏉옙- 0 : 占쎈：獄쏉옙, 1 : 占쎈뼊占쎈떄占쎈돗野껓옙

					Explosion_List.add(ex);

					// �빊�뫖猷롳옙�솇占쎌젟占쎌몵嚥≪뮇沅쀯옙�뵬筌욊쑴�읅占쎌벥占쎌맄燁살꼷肉�

					// 占쎌뵠占쎈읃占쎈뱜�몴�눘�뀤揶쏉옙占쎈립占쎈뼄.

				}

				if (Crash(ms.x, ms.y, en.x, en.y, Missile3_img, Enemy_img) && (ms.who == 3)) {

					// 占쎈탣占쎌쟿占쎌뵠占쎈선沃섎챷沅쀯옙�뵬�⑥눘�읅�빊�뫖猷롳옙�솇占쎌젟

					// 沃섎챷沅쀯옙�뵬占쎌벥�넫�슦紐닺쳸瑜곸뵠沃섎챷占쏙옙�솁占쎌뵬,

					// 占쎌읅占쎌벥�넫�슦紐닺쳸瑜곸뵠沃섎챷占쏙옙�솁占쎌뵬占쎌뱽獄쏆룇釉�

					// �빊�뫖猷롳옙�솇占쎌젟筌롫뗄�꺖占쎈굡嚥≪뮆苑녷묾怨뚰�촷rue,false揶쏅�れ뱽

					// �뵳�뗪쉘獄쏆룇釉쁳rue筌롳옙 占쎈툡占쎌삋�몴�눘�뼄占쎈뻬占쎈�占쎈빍占쎈뼄.

					en.Hit_point--;
					if (en.speed > 1) {
						en.speed--;
					}
					Missile_List.remove(i);

					if (en.Hit_point < 0) {
						Enemy_List.remove(j);
					}
					// �빊�뫖猷롳옙釉놂옙�읅占쎄텣占쎌젫

					game_Score += 10; // 野껊슣�뿫占쎌젎占쎈땾�몴占�+10占쎌젎.

					ex = new Explosion(en.x + Enemy_img.getWidth(null) / 2,

							en.y + Enemy_img.getHeight(null) / 2, 0);

					// 占쎌읅占쎌뵠占쎌맄燁살꼹鍮먲옙�뿳占쎈뮉�⑤끃�벥餓λ쵐�뼎�넫�슦紐큫,y 揶쏅�⑤궢

					// 占쎈：獄쏆뮇苑뺧옙�젟占쎌뱽獄쏆룇占썲첎占�( 0 占쎌굢占쎈뮉1 )占쎌뱽獄쏆룇�뮸占쎈빍占쎈뼄.

					// 占쎈：獄쏆뮇苑뺧옙�젟揶쏉옙- 0 : 占쎈：獄쏉옙, 1 : 占쎈뼊占쎈떄占쎈돗野껓옙

					Explosion_List.add(ex);

					// �빊�뫖猷롳옙�솇占쎌젟占쎌몵嚥≪뮇沅쀯옙�뵬筌욊쑴�읅占쎌벥占쎌맄燁살꼷肉�

					// 占쎌뵠占쎈읃占쎈뱜�몴�눘�뀤揶쏉옙占쎈립占쎈뼄.

				}

				if (Crash(ms.x, ms.y, en.x, en.y, Missile4_img, Enemy_img) && (ms.who == 4)) {

					// 占쎈탣占쎌쟿占쎌뵠占쎈선沃섎챷沅쀯옙�뵬�⑥눘�읅�빊�뫖猷롳옙�솇占쎌젟

					// 沃섎챷沅쀯옙�뵬占쎌벥�넫�슦紐닺쳸瑜곸뵠沃섎챷占쏙옙�솁占쎌뵬,

					// 占쎌읅占쎌벥�넫�슦紐닺쳸瑜곸뵠沃섎챷占쏙옙�솁占쎌뵬占쎌뱽獄쏆룇釉�

					// �빊�뫖猷롳옙�솇占쎌젟筌롫뗄�꺖占쎈굡嚥≪뮆苑녷묾怨뚰�촷rue,false揶쏅�れ뱽

					// �뵳�뗪쉘獄쏆룇釉쁳rue筌롳옙 占쎈툡占쎌삋�몴�눘�뼄占쎈뻬占쎈�占쎈빍占쎈뼄.

					en.Hit_point--;

					if (en.Hit_point < 0) {
						Enemy_List.remove(j);
					}
					// �빊�뫖猷롳옙釉놂옙�읅占쎄텣占쎌젫

					game_Score += 10; // 野껊슣�뿫占쎌젎占쎈땾�몴占�+10占쎌젎.

					ex = new Explosion(en.x + Enemy_img.getWidth(null) / 2,

							en.y + Enemy_img.getHeight(null) / 2, 0);

					// 占쎌읅占쎌뵠占쎌맄燁살꼹鍮먲옙�뿳占쎈뮉�⑤끃�벥餓λ쵐�뼎�넫�슦紐큫,y 揶쏅�⑤궢

					// 占쎈：獄쏆뮇苑뺧옙�젟占쎌뱽獄쏆룇占썲첎占�( 0 占쎌굢占쎈뮉1 )占쎌뱽獄쏆룇�뮸占쎈빍占쎈뼄.

					// 占쎈：獄쏆뮇苑뺧옙�젟揶쏉옙- 0 : 占쎈：獄쏉옙, 1 : 占쎈뼊占쎈떄占쎈돗野껓옙

					Explosion_List.add(ex);

					// �빊�뫖猷롳옙�솇占쎌젟占쎌몵嚥≪뮇沅쀯옙�뵬筌욊쑴�읅占쎌벥占쎌맄燁살꼷肉�

					// 占쎌뵠占쎈읃占쎈뱜�몴�눘�뀤揶쏉옙占쎈립占쎈뼄.

				}

			}

			for (int j = 0; j < Enemy_List2.size(); ++j) {

				// 占쎈퓠占쎄섐沃섎챶媛숋옙肉댐옙肉됬빊遺쏙옙占쎈쭆占쎌읅占쎌뵠占쎈섰筌띾뜄援뱄옙�뿳占쎈뮉筌욑옙占쎌넇占쎌뵥

				en = (Enemy) Enemy_List2.get(j);

				// 獄쏄퀣肉댐옙肉됭�곕똻�삺占쎈릭占쎈뮉占쎌읅占쎌벥揶쏆빘猿쒐몴�눖而븝옙釉섓옙�궔占쎈뼄

				if (Crash(ms.x, ms.y, en.x, en.y, Missile_img, Enemy_img) && (ms.who == 0)) {

					// 占쎈탣占쎌쟿占쎌뵠占쎈선沃섎챷沅쀯옙�뵬�⑥눘�읅�빊�뫖猷롳옙�솇占쎌젟

					// 沃섎챷沅쀯옙�뵬占쎌벥�넫�슦紐닺쳸瑜곸뵠沃섎챷占쏙옙�솁占쎌뵬,

					// 占쎌읅占쎌벥�넫�슦紐닺쳸瑜곸뵠沃섎챷占쏙옙�솁占쎌뵬占쎌뱽獄쏆룇釉�

					// �빊�뫖猷롳옙�솇占쎌젟筌롫뗄�꺖占쎈굡嚥≪뮆苑녷묾怨뚰�촷rue,false揶쏅�れ뱽

					// �뵳�뗪쉘獄쏆룇釉쁳rue筌롳옙 占쎈툡占쎌삋�몴�눘�뼄占쎈뻬占쎈�占쎈빍占쎈뼄.

					en.Hit_point--;
					Missile_List.remove(i);

					// �빊�뫖猷롳옙釉녘첋紐꾧텢占쎌뵬占쎄텣占쎌젫

					if (en.Hit_point <= 0) {
						Enemy_List.remove(j);
					}
					// �빊�뫖猷롳옙釉놂옙�읅占쎄텣占쎌젫

					game_Score += 10; // 野껊슣�뿫占쎌젎占쎈땾�몴占�+10占쎌젎.

					ex = new Explosion(en.x + Enemy_img.getWidth(null) / 2,

							en.y + Enemy_img.getHeight(null) / 2, 0);

					// 占쎌읅占쎌뵠占쎌맄燁살꼹鍮먲옙�뿳占쎈뮉�⑤끃�벥餓λ쵐�뼎�넫�슦紐큫,y 揶쏅�⑤궢

					// 占쎈：獄쏆뮇苑뺧옙�젟占쎌뱽獄쏆룇占썲첎占�( 0 占쎌굢占쎈뮉1 )占쎌뱽獄쏆룇�뮸占쎈빍占쎈뼄.

					// 占쎈：獄쏆뮇苑뺧옙�젟揶쏉옙- 0 : 占쎈：獄쏉옙, 1 : 占쎈뼊占쎈떄占쎈돗野껓옙

					Explosion_List.add(ex);

					// �빊�뫖猷롳옙�솇占쎌젟占쎌몵嚥≪뮇沅쀯옙�뵬筌욊쑴�읅占쎌벥占쎌맄燁살꼷肉�

					// 占쎌뵠占쎈읃占쎈뱜�몴�눘�뀤揶쏉옙占쎈립占쎈뼄.

				}

				if (Crash(ms.x, ms.y, en.x, en.y, Missile2_img, Enemy_img) && (ms.who == 2)) {

					// 占쎈탣占쎌쟿占쎌뵠占쎈선沃섎챷沅쀯옙�뵬�⑥눘�읅�빊�뫖猷롳옙�솇占쎌젟

					// 沃섎챷沅쀯옙�뵬占쎌벥�넫�슦紐닺쳸瑜곸뵠沃섎챷占쏙옙�솁占쎌뵬,

					// 占쎌읅占쎌벥�넫�슦紐닺쳸瑜곸뵠沃섎챷占쏙옙�솁占쎌뵬占쎌뱽獄쏆룇釉�

					// �빊�뫖猷롳옙�솇占쎌젟筌롫뗄�꺖占쎈굡嚥≪뮆苑녷묾怨뚰�촷rue,false揶쏅�れ뱽

					// �뵳�뗪쉘獄쏆룇釉쁳rue筌롳옙 占쎈툡占쎌삋�몴�눘�뼄占쎈뻬占쎈�占쎈빍占쎈뼄.

					en.Hit_point--;
					en.Hit_point--;
					en.Hit_point--;
					en.Hit_point--;
					en.Hit_point--;
					en.Hit_point--;
					Missile_List.remove(i);

					// �빊�뫖猷롳옙釉녘첋紐꾧텢占쎌뵬占쎄텣占쎌젫

					if (en.Hit_point <= 0) {
						Enemy_List.remove(j);
					}
					// �빊�뫖猷롳옙釉놂옙�읅占쎄텣占쎌젫

					game_Score += 60; // 野껊슣�뿫占쎌젎占쎈땾�몴占�+10占쎌젎.

					ex = new Explosion(en.x + Enemy_img.getWidth(null) / 2,

							en.y + Enemy_img.getHeight(null) / 2, 0);

					// 占쎌읅占쎌뵠占쎌맄燁살꼹鍮먲옙�뿳占쎈뮉�⑤끃�벥餓λ쵐�뼎�넫�슦紐큫,y 揶쏅�⑤궢

					// 占쎈：獄쏆뮇苑뺧옙�젟占쎌뱽獄쏆룇占썲첎占�( 0 占쎌굢占쎈뮉1 )占쎌뱽獄쏆룇�뮸占쎈빍占쎈뼄.

					// 占쎈：獄쏆뮇苑뺧옙�젟揶쏉옙- 0 : 占쎈：獄쏉옙, 1 : 占쎈뼊占쎈떄占쎈돗野껓옙

					Explosion_List.add(ex);

					// �빊�뫖猷롳옙�솇占쎌젟占쎌몵嚥≪뮇沅쀯옙�뵬筌욊쑴�읅占쎌벥占쎌맄燁살꼷肉�

					// 占쎌뵠占쎈읃占쎈뱜�몴�눘�뀤揶쏉옙占쎈립占쎈뼄.

				}

				if (Crash(ms.x, ms.y, en.x, en.y, Missile3_img, Enemy_img) && (ms.who == 3)) {

					// 占쎈탣占쎌쟿占쎌뵠占쎈선沃섎챷沅쀯옙�뵬�⑥눘�읅�빊�뫖猷롳옙�솇占쎌젟

					// 沃섎챷沅쀯옙�뵬占쎌벥�넫�슦紐닺쳸瑜곸뵠沃섎챷占쏙옙�솁占쎌뵬,

					// 占쎌읅占쎌벥�넫�슦紐닺쳸瑜곸뵠沃섎챷占쏙옙�솁占쎌뵬占쎌뱽獄쏆룇釉�

					// �빊�뫖猷롳옙�솇占쎌젟筌롫뗄�꺖占쎈굡嚥≪뮆苑녷묾怨뚰�촷rue,false揶쏅�れ뱽

					// �뵳�뗪쉘獄쏆룇釉쁳rue筌롳옙 占쎈툡占쎌삋�몴�눘�뼄占쎈뻬占쎈�占쎈빍占쎈뼄.

					en.Hit_point--;
					if (en.speed > 1) {
						en.speed--;
					}
					Missile_List.remove(i);

					// �빊�뫖猷롳옙釉녘첋紐꾧텢占쎌뵬占쎄텣占쎌젫

					if (en.Hit_point <= 0) {
						Enemy_List.remove(j);
					}
					// �빊�뫖猷롳옙釉놂옙�읅占쎄텣占쎌젫

					game_Score += 10; // 野껊슣�뿫占쎌젎占쎈땾�몴占�+10占쎌젎.

					ex = new Explosion(en.x + Enemy_img.getWidth(null) / 2,

							en.y + Enemy_img.getHeight(null) / 2, 0);

					// 占쎌읅占쎌뵠占쎌맄燁살꼹鍮먲옙�뿳占쎈뮉�⑤끃�벥餓λ쵐�뼎�넫�슦紐큫,y 揶쏅�⑤궢

					// 占쎈：獄쏆뮇苑뺧옙�젟占쎌뱽獄쏆룇占썲첎占�( 0 占쎌굢占쎈뮉1 )占쎌뱽獄쏆룇�뮸占쎈빍占쎈뼄.

					// 占쎈：獄쏆뮇苑뺧옙�젟揶쏉옙- 0 : 占쎈：獄쏉옙, 1 : 占쎈뼊占쎈떄占쎈돗野껓옙

					Explosion_List.add(ex);

					// �빊�뫖猷롳옙�솇占쎌젟占쎌몵嚥≪뮇沅쀯옙�뵬筌욊쑴�읅占쎌벥占쎌맄燁살꼷肉�

					// 占쎌뵠占쎈읃占쎈뱜�몴�눘�뀤揶쏉옙占쎈립占쎈뼄.

				}

				if (Crash(ms.x, ms.y, en.x, en.y, Missile4_img, Enemy_img) && (ms.who == 4)) {

					// 占쎈탣占쎌쟿占쎌뵠占쎈선沃섎챷沅쀯옙�뵬�⑥눘�읅�빊�뫖猷롳옙�솇占쎌젟

					// 沃섎챷沅쀯옙�뵬占쎌벥�넫�슦紐닺쳸瑜곸뵠沃섎챷占쏙옙�솁占쎌뵬,

					// 占쎌읅占쎌벥�넫�슦紐닺쳸瑜곸뵠沃섎챷占쏙옙�솁占쎌뵬占쎌뱽獄쏆룇釉�

					// �빊�뫖猷롳옙�솇占쎌젟筌롫뗄�꺖占쎈굡嚥≪뮆苑녷묾怨뚰�촷rue,false揶쏅�れ뱽

					// �뵳�뗪쉘獄쏆룇釉쁳rue筌롳옙 占쎈툡占쎌삋�몴�눘�뼄占쎈뻬占쎈�占쎈빍占쎈뼄.

					en.Hit_point--;

					// �빊�뫖猷롳옙釉녘첋紐꾧텢占쎌뵬占쎄텣占쎌젫

					if (en.Hit_point <= 0) {
						Enemy_List.remove(j);
					}
					// �빊�뫖猷롳옙釉놂옙�읅占쎄텣占쎌젫

					game_Score += 10; // 野껊슣�뿫占쎌젎占쎈땾�몴占�+10占쎌젎.

					ex = new Explosion(en.x + Enemy_img.getWidth(null) / 2,

							en.y + Enemy_img.getHeight(null) / 2, 0);

					// 占쎌읅占쎌뵠占쎌맄燁살꼹鍮먲옙�뿳占쎈뮉�⑤끃�벥餓λ쵐�뼎�넫�슦紐큫,y 揶쏅�⑤궢

					// 占쎈：獄쏆뮇苑뺧옙�젟占쎌뱽獄쏆룇占썲첎占�( 0 占쎌굢占쎈뮉1 )占쎌뱽獄쏆룇�뮸占쎈빍占쎈뼄.

					// 占쎈：獄쏆뮇苑뺧옙�젟揶쏉옙- 0 : 占쎈：獄쏉옙, 1 : 占쎈뼊占쎈떄占쎈돗野껓옙

					Explosion_List.add(ex);

					// �빊�뫖猷롳옙�솇占쎌젟占쎌몵嚥≪뮇沅쀯옙�뵬筌욊쑴�읅占쎌벥占쎌맄燁살꼷肉�

					// 占쎌뵠占쎈읃占쎈뱜�몴�눘�뀤揶쏉옙占쎈립占쎈뼄.

				}

			}

		}

	}

	public void EnemyProcess() {// 占쎌읅占쎌벥占쎈뻬占쎈짗�꽴占쏙옙�졃筌ｌ꼶�봺筌롫뗄�꺖占쎈굡

		for (int i = 0; i < Enemy_List.size(); ++i) {

			// 占쎈퓠占쎄섐沃섎챶媛숋옙肉댐옙肉됬빊遺쏙옙占쎈쭆野껉퍔�뵠占쎌뿳占쎈뮉筌욑옙占쎌넇占쎌뵥

			en = (Enemy) (Enemy_List.get(i));

			// 占쎈퉸占쎈뼣獄쏄퀣肉댐옙�벥占쎌읅揶쏆빘猿쒐몴�눖而븝옙釉섓옙�궔占쎈뼄.

			en.move1();

			// 占쎈퉸占쎈뼣占쎌읅占쏙옙筌욊낯�뵠疫뀐옙

			if (en.y > f_height) {

				Enemy_List.remove(i);

				// 占쎌넅筌롫�嫄방틦�슣占쏙옙猷꾬옙�뼎占쎈립占쎌읅占쎄텣占쎌젫

			}

			if (cnt % 53 == 0) {

				ms = new Missile(en.x + 25, en.y + 35, 90, missile_Speed, 1);

				// 占쎌넇占쎌뵥占쎈쭆占쎈퉸占쎈뼣占쎌읅占쎌벥占쎌맄燁살꼷肉됭첋紐꾧텢占쎌뵬占쎄문占쎄쉐

				// 占쎌뇢筌잛럥占쏙옙苑ｈ첋紐꾧텢占쎌뵬x�넫�슦紐�, y�넫�슦紐�, 沃섎챷沅쀯옙�뵬筌욊쑵六얕쳸�뫚堉�, 沃섎챷沅쀯옙�뵬占쎈꺗占쎈즲, 沃섎챷沅쀯옙�뵬�넫�굝履�

				Missile_List.add(ms);

				// 占쎄문占쎄쉐占쎈쭆沃섎챷沅쀯옙�뵬占쎌뱽揶쏆빘猿쒏에�뮆媛숋옙肉댐옙肉됬빊遺쏙옙

			}

			if (Crash(x, y, en.x, en.y, Player_img[0], Enemy_img)) {

				// 占쎈탣占쎌쟿占쎌뵠占쎈선占쏙옙占쎌읅占쎌벥�빊�뫖猷롳옙�뱽占쎈솇占쎌젟占쎈릭占쎈연

				// boolean揶쏅�れ뱽 �뵳�뗪쉘獄쏆룇釉쁳rue筌롳옙 占쎈툡占쎌삋�몴�눘�뼄占쎈뻬占쎈�占쎈빍占쎈뼄.

				player_Hitpoint--; // 占쎈탣占쎌쟿占쎌뵠占쎈선筌ｋ��젾占쎌뱽1繹먮씮�뮸占쎈빍占쎈뼄.

				Enemy_List.remove(i); // 占쎌읅占쎌뱽占쎌젫椰꾧퀬鍮�占쎈빍占쎈뼄.

				game_Score += 10;

				// 占쎌젫椰꾧퀡留귨옙�읅占쎌몵嚥≪뮄苡띰옙�뿫占쎈뮞�굜遺용선�몴占�10 筌앹빓占쏙옙�뻻占쎄땁占쎈빍占쎈뼄.

				ex = new Explosion(en.x + Enemy_img.getWidth(null) / 2, en.y

						+ Enemy_img.getHeight(null) / 2, 0);

				// 占쎌읅占쎌뵠占쎌맄燁살꼹鍮먲옙�뿳占쎈뮉�⑤끃�벥餓λ쵐�뼎�넫�슦紐큫,y 揶쏅�⑤궢

				// 占쎈：獄쏆뮇苑뺧옙�젟占쎌뱽獄쏆룇占썲첎占�( 0 占쎌굢占쎈뮉1 )占쎌뱽獄쏆룇�뮸占쎈빍占쎈뼄.

				// 占쎈：獄쏆뮇苑뺧옙�젟揶쏉옙- 0 : 占쎈：獄쏉옙, 1 : 占쎈뼊占쎈떄占쎈돗野껓옙

				Explosion_List.add(ex);

				// 占쎌젫椰꾧퀡留귨옙�읅占쎌맄燁살꼷肉됵옙猷븃쳸�뮇�뵠占쎈읃占쎈뱜�몴�눘�뀤揶쏉옙占쎈�占쎈빍占쎈뼄.

				ex = new Explosion(x, y, 1);

				// 占쎌읅占쎌뵠占쎌맄燁살꼹鍮먲옙�뿳占쎈뮉�⑤끃�벥餓λ쵐�뼎�넫�슦紐큫,y 揶쏅�⑤궢

				// 占쎈：獄쏆뮇苑뺧옙�젟占쎌뱽獄쏆룇占썲첎占�( 0 占쎌굢占쎈뮉1 )占쎌뱽獄쏆룇�뮸占쎈빍占쎈뼄.

				// 占쎈：獄쏆뮇苑뺧옙�젟揶쏉옙- 0 : 占쎈：獄쏉옙, 1 : 占쎈뼊占쎈떄占쎈돗野껓옙

				Explosion_List.add(ex);

				// �빊�뫖猷롳옙�뻻占쎈탣占쎌쟿占쎌뵠占쎈선占쎌벥占쎌맄燁살꼷肉됬빊�뫖猷롳옙�뒠占쎌뵠占쎈읃占쎈뱜�몴�눘�뀤揶쏉옙.

			}

		}

		if (cnt % 211 == 0 /* && game_Score < 500 */) {

			// �뙴�뫂遊썹㎉�똻�뒲占쎈뱜�몴�눘�뵠占쎌뒠占쎈립占쎌읅占쎈쾻占쎌삢占쏙옙占쎌뵠獄쏅씮�쒙옙�쟿

			en = new Enemy(f_width - 100, 0, enemy_speed, 3);

			Enemy_List.add(en);

			en = new Enemy(f_width - 200, -10, enemy_speed, 3);

			Enemy_List.add(en);

			en = new Enemy(f_width - 300, -15, enemy_speed, 3);

			Enemy_List.add(en);

			en = new Enemy(f_width - 400, -10, enemy_speed, 3);

			Enemy_List.add(en);

			en = new Enemy(f_width - 500, 0, enemy_speed, 3);

			Enemy_List.add(en);

			// 占쎌읅占쏙옙筌욊낯�뿫占쎈꺗占쎈즲�몴�눘�뀤揶쏉옙嚥≪뮆而븝옙釉섓옙�읅占쎌뱽占쎄문占쎄쉐占쎈립占쎈뼄.

		}

	}

	public void EnemyProcess2() {// 占쎌읅占쎌벥占쎈뻬占쎈짗�꽴占쏙옙�졃筌ｌ꼶�봺筌롫뗄�꺖占쎈굡

		for (int i = 0; i < Enemy_List2.size(); ++i) {

			// 占쎈퓠占쎄섐沃섎챶媛숋옙肉댐옙肉됬빊遺쏙옙占쎈쭆野껉퍔�뵠占쎌뿳占쎈뮉筌욑옙占쎌넇占쎌뵥

			en = (Enemy) (Enemy_List2.get(i));

			// 占쎈퉸占쎈뼣獄쏄퀣肉댐옙�벥占쎌읅揶쏆빘猿쒐몴�눖而븝옙釉섓옙�궔占쎈뼄.

			en.move2();

			// 占쎈퉸占쎈뼣占쎌읅占쏙옙筌욊낯�뵠疫뀐옙

			if (en.y > f_height) {

				Enemy_List2.remove(i);

				// 占쎌넅筌롫�嫄방틦�슣占쏙옙猷꾬옙�뼎占쎈립占쎌읅占쎄텣占쎌젫

			}

			if (cnt % 59 == 0) {

				ms = new Missile(en.x + 25, en.y + 35, 90, missile_Speed, 1);

				// 占쎌넇占쎌뵥占쎈쭆占쎈퉸占쎈뼣占쎌읅占쎌벥占쎌맄燁살꼷肉됭첋紐꾧텢占쎌뵬占쎄문占쎄쉐

				// 占쎌뇢筌잛럥占쏙옙苑ｈ첋紐꾧텢占쎌뵬x�넫�슦紐�, y�넫�슦紐�, 沃섎챷沅쀯옙�뵬筌욊쑵六얕쳸�뫚堉�, 沃섎챷沅쀯옙�뵬占쎈꺗占쎈즲, 沃섎챷沅쀯옙�뵬�넫�굝履�

				Missile_List.add(ms);

				// 占쎄문占쎄쉐占쎈쭆沃섎챷沅쀯옙�뵬占쎌뱽揶쏆빘猿쒏에�뮆媛숋옙肉댐옙肉됬빊遺쏙옙

			}

			if (Crash(x, y, en.x, en.y, Player_img[0], Enemy_img)) {

				// 占쎈탣占쎌쟿占쎌뵠占쎈선占쏙옙占쎌읅占쎌벥�빊�뫖猷롳옙�뱽占쎈솇占쎌젟占쎈릭占쎈연

				// boolean揶쏅�れ뱽 �뵳�뗪쉘獄쏆룇釉쁳rue筌롳옙 占쎈툡占쎌삋�몴�눘�뼄占쎈뻬占쎈�占쎈빍占쎈뼄.

				player_Hitpoint--; // 占쎈탣占쎌쟿占쎌뵠占쎈선筌ｋ��젾占쎌뱽1繹먮씮�뮸占쎈빍占쎈뼄.

				Enemy_List2.remove(i); // 占쎌읅占쎌뱽占쎌젫椰꾧퀬鍮�占쎈빍占쎈뼄.

				game_Score += 10;

				// 占쎌젫椰꾧퀡留귨옙�읅占쎌몵嚥≪뮄苡띰옙�뿫占쎈뮞�굜遺용선�몴占�10 筌앹빓占쏙옙�뻻占쎄땁占쎈빍占쎈뼄.

				ex = new Explosion(en.x + Enemy_img.getWidth(null) / 2, en.y

						+ Enemy_img.getHeight(null) / 2, 0);

				// 占쎌읅占쎌뵠占쎌맄燁살꼹鍮먲옙�뿳占쎈뮉�⑤끃�벥餓λ쵐�뼎�넫�슦紐큫,y 揶쏅�⑤궢

				// 占쎈：獄쏆뮇苑뺧옙�젟占쎌뱽獄쏆룇占썲첎占�( 0 占쎌굢占쎈뮉1 )占쎌뱽獄쏆룇�뮸占쎈빍占쎈뼄.

				// 占쎈：獄쏆뮇苑뺧옙�젟揶쏉옙- 0 : 占쎈：獄쏉옙, 1 : 占쎈뼊占쎈떄占쎈돗野껓옙

				Explosion_List.add(ex);

				// 占쎌젫椰꾧퀡留귨옙�읅占쎌맄燁살꼷肉됵옙猷븃쳸�뮇�뵠占쎈읃占쎈뱜�몴�눘�뀤揶쏉옙占쎈�占쎈빍占쎈뼄.

				ex = new Explosion(x, y, 1);

				// 占쎌읅占쎌뵠占쎌맄燁살꼹鍮먲옙�뿳占쎈뮉�⑤끃�벥餓λ쵐�뼎�넫�슦紐큫,y 揶쏅�⑤궢

				// 占쎈：獄쏆뮇苑뺧옙�젟占쎌뱽獄쏆룇占썲첎占�( 0 占쎌굢占쎈뮉1 )占쎌뱽獄쏆룇�뮸占쎈빍占쎈뼄.

				// 占쎈：獄쏆뮇苑뺧옙�젟揶쏉옙- 0 : 占쎈：獄쏉옙, 1 : 占쎈뼊占쎈떄占쎈돗野껓옙

				Explosion_List.add(ex);

				// �빊�뫖猷롳옙�뻻占쎈탣占쎌쟿占쎌뵠占쎈선占쎌벥占쎌맄燁살꼷肉됬빊�뫖猷롳옙�뒠占쎌뵠占쎈읃占쎈뱜�몴�눘�뀤揶쏉옙.

			}

		}

		if (cnt % 151 == 0 && game_Score > 500) {

			en = new Enemy(f_width + 50, -20, enemy_speed, 3);

			Enemy_List2.add(en);

			en = new Enemy(f_width + 150, -60, enemy_speed, 3);

			Enemy_List2.add(en);

			en = new Enemy(f_width + 250, -100, enemy_speed, 3);

			Enemy_List2.add(en);

			en = new Enemy(f_width + 350, -140, enemy_speed, 3);

			Enemy_List2.add(en);

			en = new Enemy(f_width + 450, -180, enemy_speed, 3);

			Enemy_List2.add(en);
		} /*
			 * else if (cnt % 100 == 0 && game_Score > 2000) {
			 * 
			 * en = new Enemy(f_width - 100, 0, enemy_speed + 1, 3);
			 * 
			 * Enemy_List.add(en);
			 * 
			 * en = new Enemy(f_width - 200, 0, enemy_speed + 1, 3);
			 * 
			 * Enemy_List.add(en);
			 * 
			 * en = new Enemy(f_width - 300, 0, enemy_speed + 1, 3);
			 * 
			 * Enemy_List.add(en);
			 * 
			 * en = new Enemy(f_width - 400, 0, enemy_speed + 1, 3);
			 * 
			 * Enemy_List.add(en);
			 * 
			 * en = new Enemy(f_width - 500, 0, enemy_speed + 1, 3); Enemy_List.add(en); }
			 * if (cnt % 100 == 0 && game_Score > 4000) {
			 * 
			 * en = new Enemy(f_width - 150, 50, enemy_speed + 2, 3);
			 * 
			 * Enemy_List.add(en);
			 * 
			 * en = new Enemy(f_width - 250, 50, enemy_speed + 2, 3);
			 * 
			 * Enemy_List.add(en);
			 * 
			 * en = new Enemy(f_width - 350, 50, enemy_speed + 2, 3);
			 * 
			 * Enemy_List.add(en);
			 * 
			 * en = new Enemy(f_width - 450, 50, enemy_speed + 2, 3);
			 * 
			 * Enemy_List.add(en);
			 * 
			 * en = new Enemy(f_width - 50, 50, enemy_speed + 2, 3);
			 * 
			 * Enemy_List.add(en); }
			 */

	}

	public void ExplosionProcess() {

		// 占쎈：獄쏆뮇�뵠占쎈읃占쎈뱜筌ｌ꼶�봺占쎌뒠筌롫뗄�꺖占쎈굡

		for (int i = 0; i < Explosion_List.size(); ++i) {

			ex = (Explosion) Explosion_List.get(i);

			ex.effect();

			// 占쎌뵠占쎈읃占쎈뱜占쎈막占쎈빍筌롫뗄�뵠占쎈�∽옙�뱽占쎄돌占쏙옙占쎄땀疫꿸퀣�맄占쎈퉸

			// 占쎌뵠占쎈읃占쎈뱜筌ｌ꼶�봺�빊遺쏙옙揶쏉옙獄쏆뮇源�占쎈릭筌롫똾鍮먲옙�뼣筌롫뗄�꺖占쎈굡�몴�눛�깈�빊占�.

		}

	}

	/*
	 * 
	 * 占쎄땀�겫占썸�④쑴沅쏉쭖遺용꺖占쎈굡
	 * 
	 */

	public boolean Crash(int x1, int y1, int x2, int y2, Image img1, Image img2) {

		// 疫꿸퀣�덄빊�뫖猷롳옙�솇占쎌젟占쎈꺖占쎈뮞�몴�눖占썲칰��鍮�占쎈빍占쎈뼄.

		// 占쎌뵠占쎌젫占쎌뵠沃섎챷占썼퉪占쏙옙�땾�몴�눖而�嚥≪뮆而븝옙釉섓옙鍮먲옙�뼣占쎌뵠沃섎챷占쏙옙�벥占쎄섶占쎌뵠, 占쎈꼥占쎌뵠揶쏅�れ뱽

		// 獄쏅뗀以덃�④쑴沅쏉옙鍮�占쎈빍占쎈뼄.

		boolean check = false;

		if (Math.abs((x1 + img1.getWidth(null) / 2)

				- (x2 + img2.getWidth(null) / 2)) < (img2.getWidth(null) / 2 + img1

						.getWidth(null) / 2)

				&& Math.abs((y1 + img1.getHeight(null) / 2)

						- (y2 + img2.getHeight(null) / 2)) < (img2

								.getHeight(null) / 2 + img1.getHeight(null) / 2)) {

			// 占쎌뵠沃섎챷占쏙옙瑗삼옙�뵠, 占쎈꼥占쎌뵠揶쏅�れ뱽獄쏅뗀以덅쳸�룇釉섉�④쑴沅쏉옙鍮�占쎈빍占쎈뼄.

			check = true;// 占쎌맄揶쏅�れ뵠true筌롳옙 check占쎈퓠 true�몴占� 占쎌읈占쎈뼎占쎈�占쎈빍占쎈뼄.

		} else {

			check = false;

		}

		return check; // check占쎌벥 揶쏅�れ뱽筌롫뗄�꺖占쎈굡占쎈퓠�뵳�뗪쉘占쎈뻻占쎄땁占쎈빍占쎈뼄.

	}

	/*
	 * 
	 * 占쎌뵠沃섎챷占썸뉩紐꺿봺占쎈뮉�겫占썽겫占�
	 * 
	 */

	public void paint(Graphics g) {

		// 疫꿸퀡�궚占쎌읅占쎌몵嚥≪뮉�읂占쎌뵥占쎈뱜占쎈퓠占쎄퐣占쎈뮉占쎈쐭�뇡遺얠쒔占쎈쓠筌띻낯�뱽占쎄퐬占쎌젟占쎈릭�⑥쥙毓쏙옙�쑓占쎌뵠占쎈뱜占쎈퓠域밸챶�젻筌욊쑨�젃�뵳�눘�뱽揶쏉옙占쎌죬占쎌궔占쎈뼄.

		buffImage = createImage(f_width, f_height);

		buffg = buffImage.getGraphics();

		update(g);

	}

	public void update(Graphics g) {

		Draw_Background();

		Draw_Player();

		Draw_Enemy();

		Draw_Missile();

		Draw_Creature();

		Draw_Explosion();

		Draw_StatusText();

		// 占쎈뼄筌욌뜆�읅占쎌뵥域밸챶�뵝占쏙옙筌롫뗄�꺖占쎈굡�몴�눛�꽰占쎈퉸占쎄퐣甕곌쑵�쓠占쎈퓠域밸챶�뵛占쎌뜎

		// 揶쏄낯伊뚳옙�뵠沃섎챷占썹몴�눊�젃�뵳怨뺤컭占쎈꺖占쎈굡�몴�눘肉ф묾怨쀫퓠占쎄퐣占쎈뼄占쎈뻬

		g.drawImage(buffImage, 0, 0, this);

		// 甕곌쑵�쓠占쎈퓠域밸챶�젻筌욊쑴�뵠沃섎챷占썹몴�눊占쏙옙議뉛옙占쏙옙�뼄占쎌젫占쎌넅筌롫똻肉됭퉪�똻�뵠野껊슡�젃�뵳怨뺣뼄.

	}

	public void Draw_Background() {

		// 獄쏄퀗瑗랃옙�뵠沃섎챷占썹몴�눊�젃�뵳�됰뮉�겫占썽겫袁⑹뿯占쎈빍占쎈뼄.

		// buffg.clearRect(0, 0, f_width, f_height);

		// 占쎌넅筌롫똻占쏙옙�뒭疫꿸퀡梨몌옙議딉옙占쏙옙�뵠占쎌젫占쎈연疫꿸퀣苑뚳옙�뼄占쎈뻬占쎈�占쎈빍占쎈뼄.

		if (bx < 0) {

			// 疫꿸퀡�궚揶쏅�れ뵠-1000占쎌뵥bx揶쏉옙 0 癰귣��뼄占쎌삂占쎌몵筌롫똻�뼄占쎈뻬

			buffg.drawImage(BackGround_img, 0, bx, this);

			bx += 1;

			// bx�몴占� -1000占쎈퓠占쎄퐣+1筌띾슦寃��④쑴�꺗占쎈뮎�뵳�됵옙嚥≪뮆媛쇿칰�럩�뵠沃섎챷占쏙옙�벥y�넫�슦紐댐옙�뮉

			// �④쑴�꺗占쎈툡占쎌삋筌잛럩�몵嚥≪뮇�뵠占쎈짗占쎈립占쎈뼄. 域밸챶�쑎沃섓옙嚥≪뮇�읈筌ｋ�媛쇿칰�럩占쏙㎗�뮇荑뉛옙�뿳

			// 占쎈툡占쎌삋筌잛럩�몵嚥≪뮇占쏙쭪怨몄뵠野껊슢留귨옙�뼄.

		} else {

			bx = -1000;

		}

		for (int i = 0; i < cx.length; ++i) {

			if (cx[i] < 1400) {

				cx[i] += 5 + i * 3;

			} else {

				cx[i] = 0;

			}

			buffg.drawImage(Cloud_img[i], 1200 - cx[i], 50 + i * 200, this);

			// 3揶쏆뮇�벥�뤃�됱カ占쎌뵠沃섎챷占썹몴�눊而뽪묾怨뺣뼄�몴紐꾨꺗占쎈즲揶쏅�れ몵嚥≪뮇伊뽳㎘�돦�몵嚥≪뮇占쏙쭪怨몄뿫.

		}

	}

	public void Draw_Player() {

		// 占쎈탣占쎌쟿占쎌뵠占쎈선筌�癒��봼占쎄숲�몴�눊�젃�뵳�됰뮉�겫占썽겫占�

		switch (player_Status) {// 占쎈탣占쎌쟿占쎌뵠占쎈선占쎌벥占쎄맒占쎄묶�몴�눘猿쒙옙寃뺧옙釉놂옙�뼄

		case 0: // 占쎈즸占쎄맒占쎈뻻

			if ((cnt / 5 % 2) == 0) {

				buffg.drawImage(Player_img[1], x, y, this);

			} else {

				buffg.drawImage(Player_img[2], x, y, this);

			}

			break;

		case 1: // 沃섎챷沅쀯옙�뵬獄쏆뮇沅�

			if ((cnt / 5 % 2) == 0) {

				buffg.drawImage(Player_img[3], x, y, this);

			} else {

				buffg.drawImage(Player_img[4], x, y, this);

			}

			// 沃섎챷沅쀯옙�뵬占쎌뱽占쎈짒占쎈뮉占쎈쾹占쎈립占쎌뵠沃섎챷占썹몴�눖苡꿨첎�뜆釉섉뉩紐껋젻餓ο옙占쎈뼄.

			player_Status = 0;

			// 沃섎챷沅쀯옙�뵬占쎈짒疫꿸퀗占쏙옙嫄뱄옙援뱄쭖�똾逾놅옙�쟿占쎌뵠占쎈선占쎄맒占쎄묶�몴占�0占쎌몵嚥≪뮆猷롧뵳怨뺣뼄.

			break;

		case 2: // �빊�뫖猷�

			break;

		}

	}

	public void Draw_Missile() {

		// 沃섎챷沅쀯옙�뵬占쎌뵠沃섎챷占썹몴�눊�젃�뵳�됰뮉�겫占썽겫占�

		for (int i = 0; i < Missile_List.size(); ++i) {

			// 沃섎챷沅쀯옙�뵬獄쏄퀣肉댐옙肉됧첎誘れ뵠鈺곕똻�삺占쎈릭筌롳옙

			ms = (Missile) (Missile_List.get(i));

			// 揶쏆빘猿쒙옙�넅占쎈뻻�녹뮆媛숋옙肉댐옙肉됵옙占쏙옙�삢占쎈쭆沃섎챷沅쀯옙�뵬占쎌뱽占쎈릭占쎄돌占쎈뎃揶쏉옙占쎌죬占쎌궔占쎈뼄

			if (ms.who == 0)
				buffg.drawImage(Missile_img, ms.x, ms.y, this);

			// 占쎈탣占쎌쟿占쎌뵠占쎈선揶쏉옙獄쏆뮇沅쀯옙釉놂옙�뵠沃섎챷占썹몴�눊�젃�뵳怨뺣뼄.

			if (ms.who == 1)
				buffg.drawImage(Missile2_img, ms.x, ms.y, this);

			// 占쎌읅占쎌뵠獄쏆뮇沅쀯옙釉놂옙�뵠沃섎챷占썹몴�눊�젃�뵳怨뺣뼄.

			if (ms.who == 2)
				buffg.drawImage(Missile3_img, ms.x, ms.y, this);

			// �겫�뜆�꺗占쎄쉐占쎄쾿�뵳�딇�쒎첎占� 獄쏆뮇沅쀯옙釉� 沃섎챷沅쀯옙�뵬

			if (ms.who == 3)
				buffg.drawImage(Missile4_img, ms.x, ms.y, this);

			// 占쎈섰占쎌벉占쎈꺗占쎄쉐占쎄쾿�뵳�딇�쒎첎占� 獄쏆뮇沅쀯옙釉� 沃섎챷沅쀯옙�뵬

			if (ms.who == 4)
				buffg.drawImage(Missile5_img, ms.x, ms.y, this);

			// 甕곕뜃而삼옙�꺗占쎄쉐占쎄쾿�뵳�딇�쒎첎占� 獄쏆뮇沅쀯옙釉� 沃섎챷沅쀯옙�뵬
		}

	}

	public void Draw_Creature() {

		switch (player_Status) {// 占쎈탣占쎌쟿占쎌뵠占쎈선占쎌벥占쎄맒占쎄묶�몴�눘猿쒙옙寃뺧옙釉놂옙�뼄

		case 0: // 占쎈즸占쎄맒占쎈뻻

			if (((cnt / 5 % 2) == 0) && creature_Status == 1) {

				buffg.drawImage(Creature_img[1], x + 30, y - 10, this);

			} else if (((cnt / 5 % 2) != 0) && creature_Status == 1) {

				buffg.drawImage(Creature_img[2], x + 30, y - 10, this);

			} else if (((cnt / 5 % 2) == 0) && creature_Status == 2) {

				buffg.drawImage(Creature_img[3], x + 30, y - 10, this);

			} else if (((cnt / 5 % 2) != 0) && creature_Status == 2) {

				buffg.drawImage(Creature_img[4], x + 30, y - 10, this);

			} else if (((cnt / 5 % 2) == 0) && creature_Status == 3) {

				buffg.drawImage(Creature_img[5], x + 30, y - 10, this);

			} else if (((cnt / 5 % 2) != 0) && creature_Status == 3) {

				buffg.drawImage(Creature_img[6], x + 30, y - 10, this);

			}

			break;

		case 2: // �빊�뫖猷�

			break;

		}

	}

	public void Draw_Enemy() {

		// 占쎌읅占쎌뵠沃섎챷占썹몴�눊�젃�뵳�됰뮉�겫占썽겫占�

		for (int i = 0; i < Enemy_List.size(); ++i) {

			en = (Enemy) (Enemy_List.get(i));

			buffg.drawImage(Enemy_img, en.x, en.y, this);
		}

		for (int i = 0; i < Enemy_List2.size(); ++i) {

			en = (Enemy) (Enemy_List2.get(i));

			buffg.drawImage(Enemy_img, en.x, en.y, this);
		}
	}

	public void Draw_Explosion() {

		// 占쎈：獄쏆뮇�뵠占쎈읃占쎈뱜�몴�눊�젃�뵳�됰뮉�겫占썽겫袁⑹뿯占쎈빍占쎈뼄.

		for (int i = 0; i < Explosion_List.size(); ++i) {

			ex = (Explosion) Explosion_List.get(i);

			// 占쎈：獄쏆뮇�뵠占쎈읃占쎈뱜占쎌벥鈺곕똻�삺占쎌��눧��占쎌눘猿쒙옙寃뺧옙釉�占쎈연�뵳�딅뮞占쎈뱜�몴�눖而븝옙�벉.

			if (ex.damage == 0) {

				// 占쎄퐬占쎌젟揶쏅�れ뵠0 占쎌뵠筌롫똾猷븃쳸�뮇�뒠占쎌뵠沃섎챷占썸뉩紐꺿봺疫뀐옙

				if (ex.ex_cnt <= 7) {

					buffg.drawImage(Explo_img[0],

							ex.x - Explo_img[0].getWidth(null) / 2, ex.y

									- Explo_img[0].getHeight(null) / 2,
							this);

				} else if (ex.ex_cnt < 14) {

					buffg.drawImage(Explo_img[1],

							ex.x - Explo_img[1].getWidth(null) / 2, ex.y

									- Explo_img[1].getHeight(null) / 2,
							this);

				} else if (ex.ex_cnt < 21) {

					buffg.drawImage(Explo_img[2],

							ex.x - Explo_img[2].getWidth(null) / 2, ex.y

									- Explo_img[2].getHeight(null) / 2,
							this);

				} else if (ex.ex_cnt >= 21) {

					Explosion_List.remove(i);

					ex.ex_cnt = 0;

					// 占쎈：獄쏆뮇占쏙옙逾꿩에�뮇萸낉옙�뒲占쎄숲�몴�눊�롳옙沅쏉옙釉�占쎈연

					// 占쎌뵠沃섎챷占썹몴�눘�떄筌△뫁�읅占쎌몵嚥≪뮄�젃�뵳占�.

				}

			} else { // 占쎄퐬占쎌젟揶쏅�れ뵠1占쎌뵠筌롫��뼊占쎈떄占쎈돗野꺿뫗�뒠占쎌뵠沃섎챷占썸뉩紐꺿봺疫뀐옙

				if (ex.ex_cnt <= 7) {

					buffg.drawImage(Explo_img[0], ex.x + 120, ex.y + 15, this);

				} else if (ex.ex_cnt < 14) {

					buffg.drawImage(Explo_img[1], ex.x + 60, ex.y + 5, this);

				} else if (ex.ex_cnt < 21) {

					buffg.drawImage(Explo_img[0], ex.x + 5, ex.y + 10, this);

				} else if (ex.ex_cnt >= 21) {

					Explosion_List.remove(i);

					ex.ex_cnt = 0;

					// 占쎈뼊占쎈떄占쎈돗野꺿뫖�굢占쎈립占쎈떄筌△뫁�읅占쎌몵嚥≪뮇�뵠沃섎챷占썹몴�눊�젃�뵳�딉옙筌랃옙

					// �뤃�됲뀋占쎌뱽占쎌맄占쎈퉸占쎈튋揶쏄쑬�뼄�몴紐껉컩占쎈뻼占쎌몵嚥≪뮄�젃�뵳�럥�빍占쎈뼄.

				}

			}

		}

	}

	public void Draw_StatusText() { // 占쎄맒占쎄묶筌ｋ똾寃뺧옙�뒠占쎈�볩옙�뮞占쎈뱜�몴�눊�젃�뵳�럥�빍占쎈뼄.

		buffg.setFont(new Font("Defualt", Font.BOLD, 20));

		// 占쎈？占쎈뱜占쎄퐬占쎌젟占쎌뱽占쎈�占쎈빍占쎈뼄. 疫꿸퀡�궚占쎈？占쎈뱜, �뤃�벀苡�, 占쎄텢占쎌뵠筌앾옙20

		buffg.drawString("SCORE : " + game_Score, 10, 70);

		// �넫�슦紐큫 : 1000, y : 70占쎈퓠占쎈뮞�굜遺용선�몴�눛紐댐옙�뻻占쎈�占쎈빍占쎈뼄.

		buffg.drawString("HitPoint : " + player_Hitpoint, 10, 90);

		// �넫�슦紐큫 : 1000, y : 90占쎈퓠占쎈탣占쎌쟿占쎌뵠占쎈선筌ｋ��젾占쎌뱽占쎈ご占쎈뻻占쎈�占쎈빍占쎈뼄.

		if (game_mode == 4) {
			waiting_play = false;
			gameOver();
		}

	}

	/*
	 * 
	 * 占쎄텕癰귣�諭띰옙�뿯占쎌젾筌ｌ꼶�봺�겫占썽겫占�
	 * 
	 */

	public void KeyProcess() {

		if (KeyUp == true) {

			if (y > 20)

				y -= 5;

			// 筌�癒��봼占쎄숲揶쏉옙癰귣똻肉э쭪占쏙옙�뮉占쎌넅筌롫똻�맄嚥≪뮆�걵占쎄퐜占쎈선揶쏉옙野껊슦鍮�占쎈빍占쎈뼄.

			player_Status = 0;

			// 占쎌뵠占쎈짗占쎄텕揶쏉옙占쎈땭占쎌젻筌욑옙筌롫똾逾놅옙�쟿占쎌뵠占쎈선占쎄맒占쎄묶�몴占�0占쎌몵嚥≪뮆猷롧뵳�럥�빍占쎈뼄.

		}

		if (KeyDown == true) {

			if (y + Player_img[0].getHeight(null) < f_height)

				y += 5;

			// 筌�癒��봼占쎄숲揶쏉옙癰귣똻肉э쭪占쏙옙�뮉占쎌넅筌롫똻釉섓옙�삋嚥≪뮆�걵占쎄퐜占쎈선揶쏉옙野껊슦鍮�占쎈빍占쎈뼄.

			player_Status = 0;

			// 占쎌뵠占쎈짗占쎄텕揶쏉옙占쎈땭占쎌젻筌욑옙筌롫똾逾놅옙�쟿占쎌뵠占쎈선占쎄맒占쎄묶�몴占�0占쎌몵嚥≪뮆猷롧뵳�럥�빍占쎈뼄.

		}

		if (KeyLeft == true) {

			if (x > 0)

				x -= 5;

			// 筌�癒��봼占쎄숲揶쏉옙癰귣똻肉э쭪占쏙옙�뮉占쎌넅筌롫똻�뇢筌잛럩�몵嚥≪뮆�걵占쎄퐜占쎈선揶쏉옙野껊슦鍮�占쎈빍占쎈뼄.

			player_Status = 0;

			// 占쎌뵠占쎈짗占쎄텕揶쏉옙占쎈땭占쎌젻筌욑옙筌롫똾逾놅옙�쟿占쎌뵠占쎈선占쎄맒占쎄묶�몴占�0占쎌몵嚥≪뮆猷롧뵳�럥�빍占쎈뼄.

		}

		if (KeyRight == true) {

			if (x + Player_img[0].getWidth(null) < f_width)

				x += 5;

			// 筌�癒��봼占쎄숲揶쏉옙癰귣똻肉э쭪占쏙옙�뮉占쎌넅筌롫똻�궎�몴紐꾠걹占쎌몵嚥≪뮆�걵占쎄퐜占쎈선揶쏉옙野껊슦鍮�占쎈빍占쎈뼄.

			player_Status = 0;

			// 占쎌뵠占쎈짗占쎄텕揶쏉옙占쎈땭占쎌젻筌욑옙筌롫똾逾놅옙�쟿占쎌뵠占쎈선占쎄맒占쎄묶�몴占�0占쎌몵嚥≪뮆猷롧뵳�럥�빍占쎈뼄.

		}

	}

	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {

		case KeyEvent.VK_UP:

			KeyUp = true;

			break;

		case KeyEvent.VK_DOWN:

			KeyDown = true;

			break;

		case KeyEvent.VK_LEFT:

			KeyLeft = true;

			break;

		case KeyEvent.VK_RIGHT:

			KeyRight = true;

			break;

		case KeyEvent.VK_SPACE:

			KeySpace = true;

			break;

		}

	}

	public void keyReleased(KeyEvent e) {

		switch (e.getKeyCode()) {

		case KeyEvent.VK_UP:

			KeyUp = false;

			break;

		case KeyEvent.VK_DOWN:

			KeyDown = false;

			break;

		case KeyEvent.VK_LEFT:

			KeyLeft = false;

			break;

		case KeyEvent.VK_RIGHT:

			KeyRight = false;

			break;

		case KeyEvent.VK_SPACE:

			KeySpace = false;

			break;

		}

	}

	public void keyTyped(KeyEvent e) {
	}

	// 癰귣㈇苡띰옙�뿫占쎈퓠占쎄퐣占쎈뮉占쎄텢占쎌뒠占쎈릭筌욑옙占쎈륫占쎈뮉筌롫뗄�꺖占쎈굡占쎌뵠占쎄돌

	// 占쎄텕�뵳�딅뮞占쎄섐�몴�눘湲쏙옙�꺗獄쏆룇釉�疫꿸퀡釉ｈ눧紐꾨퓠疫꿸퀡�궚占쎌읅占쎌몵嚥≪뮇源�占쎄쉐占쎈퉸占쎈쳥占쎈튊

	// 占쎈퓠占쎌쑎揶쏉옙獄쏆뮇源�占쎈릭筌욑옙占쎈륫占쎈뮉占쎈뼄.

}

/*
 * 
 * 揶쏆빘猿쒙옙�넅�몴�눘�맄占쎈립占쎄깻占쎌삋占쎈뮞�꽴占썹뵳�됵옙�겫占�
 * 
 */

class Missile {

	// 占쎈연占쎌쑎揶쏆뮇�벥沃섎챷沅쀯옙�뵬占쎌뵠沃섎챷占썹몴�눊�젃�뵳�덈┛占쎌맄占쎈퉸占쎄깻占쎌삋占쎈뮞�몴�눘�뀤揶쏉옙占쎈릭占쎈연揶쏆빘猿쒏꽴占썹뵳占�

	int x;// 沃섎챷沅쀯옙�뵬占쎌겱占쎌삺x �넫�슦紐댐옙�뒠癰귨옙占쎈땾

	int y; // 沃섎챷沅쀯옙�뵬占쎌겱占쎌삺y �넫�슦紐댐옙�뒠癰귨옙占쎈땾

	int angle; // 沃섎챷沅쀯옙�뵬占쎌뵠占쎄텊占쎌뵬揶쏉옙占쎈뮉獄쎻뫚堉�占쎈솇癰귢쑴�뱽占쎌맄占쎈립癰귨옙占쎈땾

	int speed; // 沃섎챷沅쀯옙�뵬占쏙옙筌욊낯�뿫占쎈꺗占쎈즲癰귨옙占쎈땾

	int who;// 沃섎챷沅쀯옙�뵬占쎌뵠獄쏆뮇沅쀯옙釉녑칰猿뗭뵠占쎈듇�뤃怨쀯옙�뤃�됲뀋占쎈릭占쎈뮉癰귨옙占쎈땾

	Missile(int x, int y, int angle, int speed, int who) {

		this.x = x;

		this.y = y;

		this.who = who;

		// �빊遺쏙옙占쎈쭆癰귨옙占쎈땾�몴�눖而븝옙釉섓옙�긿占쎈빍占쎈뼄.

		this.angle = angle;

		this.speed = speed;

	}

	public void move() {

		x += Math.cos(Math.toRadians(angle)) * speed;

		// 占쎈퉸占쎈뼣獄쎻뫚堉�占쎌몵嚥≪뮆占쎈챷沅쀯옙�뵬獄쏆뮇沅�.

		y += Math.sin(Math.toRadians(angle)) * speed;

		// 占쎈퉸占쎈뼣獄쎻뫚堉�占쎌몵嚥≪뮆占쎈챷沅쀯옙�뵬獄쏆뮇沅�.

	}

}

class Enemy {

	// 占쎈연占쎌쑎揶쏆뮇�벥占쎌읅占쎌뵠沃섎챷占썹몴�눊�젃�뵳�덈┛占쎌맄占쎈퉸占쎄깻占쎌삋占쎈뮞�몴�눘�뀤揶쏉옙占쎈릭占쎈연揶쏆빘猿쒏꽴占썹뵳占�

	int x;// 占쎌읅占쎌겱占쎌삺x �넫�슦紐댐옙�뒠癰귨옙占쎈땾

	int y;// 占쎌읅占쎌겱占쎌삺y �넫�슦紐댐옙�뒠癰귨옙占쎈땾

	int speed; // 占쎌읅占쎌뵠占쎈짗占쎈꺗占쎈즲癰귨옙占쎈땾�몴�눘�뀤揶쏉옙

	int Hit_point; // 占쎌읅 筌ｋ��젾癰귨옙占쎈땾 �빊遺쏙옙

	Enemy(int x, int y, int speed, int Hit_point) {

		this.x = x;

		this.y = y;

		this.speed = speed;

		this.Hit_point = Hit_point;

	}

	public void move1() {

		y += speed;// 占쎌읅占쎌뵠占쎈짗占쎈꺗占쎈즲筌띾슦寃�占쎌뵠占쎈짗
	}

	public void move2() {

		x -= speed + 1;

		y += speed;// 占쎌읅占쎌뵠占쎈짗占쎈꺗占쎈즲筌띾슦寃�占쎌뵠占쎈짗
	}
}

class Explosion {

	// 占쎈연占쎌쑎揶쏆뮇�벥占쎈：獄쏆뮇�뵠沃섎챷占썹몴�눊�젃�뵳�덈┛占쎌맄占쎈퉸占쎄깻占쎌삋占쎈뮞�몴�눘�뀤揶쏉옙占쎈릭占쎈연揶쏆빘猿쒏꽴占썹뵳占�

	int x; // 占쎌뵠沃섎챷占썹몴�눊�젃�뵳�겓 �넫�슦紐�

	int y; // 占쎌뵠沃섎챷占썹몴�눊�젃�뵳�겗 �넫�슦紐�

	int ex_cnt; // 占쎌뵠沃섎챷占썹몴�눘�떄筌△뫁�읅占쎌몵嚥≪뮄�젃�뵳�덈┛占쎌맄占쎈립燁삳똻�뒲占쎄숲

	int damage; // 占쎌뵠沃섎챷占썽넫�굝履잏몴�눊�럡�겫袁る릭疫꿸퀣�맄占쎈립癰귨옙占쎈땾揶쏉옙

	Explosion(int x, int y, int damage) {

		this.x = x;

		this.y = y;

		this.damage = damage;

		ex_cnt = 0;

	}

	public void effect() {

		ex_cnt++; // 占쎈퉸占쎈뼣筌롫뗄�꺖占쎈굡占쎌깈�빊�뮇�뻻燁삳똻�뒲占쎄숲�몴占�+1 占쎈뻻占쎄텚占쎈뼄.

	}

}
