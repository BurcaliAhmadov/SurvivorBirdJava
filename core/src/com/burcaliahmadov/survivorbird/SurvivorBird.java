package com.burcaliahmadov.survivorbird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class SurvivorBird extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Texture friend;
	Texture enemy1;
	Texture enemy2;
	Texture enemy3;
	float birdX=0;
	float birdY=0;
	int gameState=0;
	float velocity=0;
	float gravity=1.5f;
	int numberOfEnemies=4;
	float enemyVelocity=10;
	float distance =0;
	Random random;
	Circle birdCircle;
	int score=0;
	int scoredEnemy=0;
	BitmapFont font;
	BitmapFont font2;

	//ShapeRenderer shapeRenderer;




	float[] enemyX= new float[numberOfEnemies];
	float[] enemySet1=new float[numberOfEnemies];
	float[] enemySet2=new float[numberOfEnemies];
	float[] enemySet3=new float[numberOfEnemies];

	Circle[] enemyCircle1;
	Circle[] enemyCircle2;
	Circle[] enemyCircle3;

	
	@Override
	public void create () {
		batch=new SpriteBatch();
		background=new Texture("background.png");
		friend= new Texture("friend.png");
		birdX=Gdx.graphics.getWidth()/4;
		birdY=Gdx.graphics.getHeight()/2;
		distance =Gdx.graphics.getWidth()/2;


		font=new BitmapFont();
		font.setColor(Color.WHITE);
		font.getData().setScale(4);
		font2=new BitmapFont();
		font2.setColor(Color.BLACK);
		font2.getData().setScale(10);

		random=new Random();
		enemy1=new Texture("enemy.png");
		enemy2=new Texture("enemy.png");
		enemy3=new Texture("enemy.png");
		birdCircle=new Circle();
		enemyCircle1=new Circle[numberOfEnemies];
		enemyCircle2=new Circle[numberOfEnemies];
		enemyCircle3= new Circle[numberOfEnemies];

		//shapeRenderer=new ShapeRenderer();

		for(int i=0 ;i<numberOfEnemies; i++){
			enemySet1[i]=(random.nextFloat()-0.5f) *  (Gdx.graphics.getHeight()-200);
			enemySet2[i]=(random.nextFloat()-0.5f)* (Gdx.graphics.getHeight()-200);
			enemySet3[i]=(random.nextFloat()-0.5f)* (Gdx.graphics.getHeight()-200);

			enemyX[i]=Gdx.graphics.getWidth() - enemy1.getWidth() / 2 + i * distance;

			enemyCircle1[i]=new Circle();
			enemyCircle2[i]=new Circle();
			enemyCircle3[i]=new Circle();

		}

	}

	@Override
	public void render () {
		batch.begin();

		batch.draw(background,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());


		if(gameState==1  ){


			if(enemyX[scoredEnemy]<Gdx.graphics.getWidth()/4){
				score++;
				if(scoredEnemy<numberOfEnemies-1){
					scoredEnemy++;
				}else{
					scoredEnemy=0;
				}
			}







			for(int i=0; i<numberOfEnemies;i++){

				if (enemyX[i]<Gdx.graphics.getWidth()/15){
					enemyX[i]=enemyX[i]+numberOfEnemies*distance;
					enemySet1[i]=(random.nextFloat()-0.5f) *  (Gdx.graphics.getHeight()-200);
					enemySet2[i]=(random.nextFloat()-0.5f)* (Gdx.graphics.getHeight()-200);
					enemySet3[i]=(random.nextFloat()-0.5f)* (Gdx.graphics.getHeight()-200);
				}else {
					enemyX[i]=enemyX[i]-enemyVelocity;
				}


				batch.draw(enemy1,enemyX[i],Gdx.graphics.getHeight()/2 +enemySet1[i],Gdx.graphics.getWidth()/15,Gdx.graphics.getHeight()/10);
				batch.draw(enemy2,enemyX[i],Gdx.graphics.getHeight()/2 +enemySet2[i],Gdx.graphics.getWidth()/15,Gdx.graphics.getHeight()/10);
				batch.draw(enemy3,enemyX[i],Gdx.graphics.getHeight()/2 +enemySet3[i],Gdx.graphics.getWidth()/15,Gdx.graphics.getHeight()/10);

				enemyCircle1[i].set(enemyX[i]+Gdx.graphics.getWidth()/30,Gdx.graphics.getHeight()/2 +enemySet1[i]+Gdx.graphics.getWidth()/30,Gdx.graphics.getWidth()/30);
				enemyCircle2[i].set(enemyX[i]+Gdx.graphics.getWidth()/30,Gdx.graphics.getHeight()/2 +enemySet2[i]+Gdx.graphics.getWidth()/30,Gdx.graphics.getWidth()/30);
				enemyCircle3[i].set(enemyX[i]+Gdx.graphics.getWidth()/30,Gdx.graphics.getHeight()/2 +enemySet3[i]+Gdx.graphics.getWidth()/30,Gdx.graphics.getWidth()/30);
			}


			if(Gdx.input.isTouched()){
				velocity=-20;
			}



			if(birdY>0||velocity<0){
				velocity=velocity+gravity;
				birdY=birdY-velocity;
			}

		}
		else if (gameState==0){
			if(Gdx.input.isTouched()){
				gameState=1;
			}
		}else if(gameState==2){
			if(Gdx.input.isTouched()) {
				gameState = 1;
				birdY = Gdx.graphics.getHeight() / 2;
				for (int i = 0; i < numberOfEnemies; i++) {
					enemySet1[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
					enemySet2[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
					enemySet3[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);

					enemyX[i] = Gdx.graphics.getWidth() - enemy1.getWidth() / 2 + i * distance;

					enemyCircle1[i] = new Circle();
					enemyCircle2[i] = new Circle();
					enemyCircle3[i] = new Circle();

				}
				velocity=0;
				score=0;
				scoredEnemy=0;
			}
		}

		batch.draw(friend,birdX,birdY,Gdx.graphics.getWidth()/15,Gdx.graphics.getHeight()/10);
		font.draw(batch,String.valueOf(score),100,200);
		if(gameState==2){
			font2.draw(batch,("Game Over!Tap to play Again"),100,Gdx.graphics.getHeight()/2);
		}


		batch.end();
		birdCircle.set(birdX+Gdx.graphics.getWidth()/30,birdY+Gdx.graphics.getHeight()/20,Gdx.graphics.getWidth()/30);
		//shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		//shapeRenderer.setColor(Color.BLACK);
		//shapeRenderer.circle(birdCircle.x,birdCircle.y,birdCircle.radius);
		for(int i=0;i<numberOfEnemies;i++){
			//shapeRenderer.circle(enemyCircle1[i].x,enemyCircle1[i].y,enemyCircle1[i].radius);
			//shapeRenderer.circle(enemyCircle2[i].x,enemyCircle2[i].y,enemyCircle2[i].radius);
			//shapeRenderer.circle(enemyCircle3[i].x,enemyCircle3[i].y,enemyCircle3[i].radius);


			if(Intersector.overlaps(birdCircle,enemyCircle1[i])|| Intersector.overlaps(birdCircle,enemyCircle2[i])||Intersector.overlaps(birdCircle,enemyCircle3[i])){
				gameState=2;
			}
		}


		//shapeRenderer.end();


	}
	
	@Override
	public void dispose () {

	}
}
