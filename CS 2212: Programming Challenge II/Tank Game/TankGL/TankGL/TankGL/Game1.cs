using System;
using System.Collections.Generic;
using System.Linq;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Audio;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.GamerServices;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;
using Microsoft.Xna.Framework.Media;
using TankGL.AI;
using TankGL.GUI;

namespace TankGL
{
    /// <summary>
    /// This is the main type for your game
    /// </summary>
    public class Game1 : Microsoft.Xna.Framework.Game
    {
        GraphicsDeviceManager graphics;
        SpriteBatch spriteBatch;
        Texture2D backgroundTexture, brickTexture, stoneTexture, waterTexture,
            healthPackTexture, coinPileTexture, bulletTexture, sandTexture, scoreCardTexture,
            tankTexture;
        GraphicsDevice device;
        const bool resultionIndependent = false;
        Vector2 baseScreenSize = new Vector2(Constants.SCREEN_HEIGHT, Constants.SCREEN_WIDTH);
        int screenWidth;
        int screenHeight;
        Rectangle[,] tiles = new Rectangle[Constants.GRID_SIZE, Constants.GRID_SIZE];
        SpriteFont font;
        Bot myBot;



        /// <summary>
        /// For test////
        /// </summary>
        List<Obstacle> stoneobs = new List<Obstacle>();
        List<Obstacle> waterobs = new List<Obstacle>();
        List<CoinPile> coinpiles = new List<CoinPile>();
        List<Brick> bricks = new List<Brick>();
        List<HealthPack> healthpacks = new List<HealthPack>();
        List<Bullet> bullets = new List<Bullet>();
        Tank[] t = new Tank[5];

        public Game1()
        {
            graphics = new GraphicsDeviceManager(this);
            Content.RootDirectory = "Content";
        }

        /// <summary>
        /// Allows the game to perform any initialization it needs to before starting to run.
        /// This is where it can query for any required services and load any non-graphic
        /// related content.  Calling base.Initialize will enumerate through any components
        /// and initialize them as well.
        /// </summary>
        protected override void Initialize()
        {
            // TODO: Add your initialization logic here
            graphics.PreferredBackBufferWidth = Constants.SCREEN_WIDTH;
            graphics.PreferredBackBufferHeight = Constants.SCREEN_HEIGHT;
            graphics.IsFullScreen = false;
            graphics.ApplyChanges();
            Window.Title = "Tank Game GUI";
            for (int i = 0; i < Constants.GRID_SIZE; i++)
            {
                for (int j = 0; j < Constants.GRID_SIZE; j++)
                {
                    tiles[i, j] = new Rectangle((i * Constants.SQUARESIZE) + 30,
                        (j * Constants.SQUARESIZE) + 30, Constants.SQUARESIZE, Constants.SQUARESIZE);
                }
            }
            font = Content.Load<SpriteFont>("myFont");
            myBot = new Bot();
            stoneobs = Bot.stone;
            bricks = Bot.bricks;
            waterobs = Bot.water;
            coinpiles = Bot.coins;
            healthpacks = Bot.healthPack;
       //    t = Bot.getMyTank();

            base.Initialize();
        }

        /// <summary>
        /// LoadContent will be called once per game and is the place to load
        /// all of your content.
        /// </summary>
        protected override void LoadContent()
        {
            // Create a new SpriteBatch, which can be used to draw textures.
            spriteBatch = new SpriteBatch(GraphicsDevice);

            // TODO: use this.Content to load your game content here
            device = graphics.GraphicsDevice;
            backgroundTexture = Content.Load<Texture2D>("background");
            brickTexture = Content.Load<Texture2D>("Brick");
            coinPileTexture = Content.Load<Texture2D>("Coins");
            sandTexture = Content.Load<Texture2D>("Sand");
            stoneTexture = Content.Load<Texture2D>("Stone");
            waterTexture = Content.Load<Texture2D>("Water");
            bulletTexture = Content.Load<Texture2D>("rocket");
            healthPackTexture = Content.Load<Texture2D>("healthPack");
            scoreCardTexture = Content.Load<Texture2D>("scoreCard");
            tankTexture = Content.Load<Texture2D>("tank");

            if (resultionIndependent)
            {
                screenWidth = (int)baseScreenSize.X;
                screenHeight = (int)baseScreenSize.Y;
            }
            else
            {
                screenWidth = device.PresentationParameters.BackBufferWidth;
                screenHeight = device.PresentationParameters.BackBufferHeight;
            }

            stoneobs.Add(new Obstacle("2,3"));
            stoneobs.Add(new Obstacle("3,5"));
            stoneobs.Add(new Obstacle("2,6"));
            waterobs.Add(new Obstacle("4,5"));
            waterobs.Add(new Obstacle("4,7"));
            waterobs.Add(new Obstacle("8,5"));
            coinpiles.Add(new CoinPile("3,3"));
            coinpiles.Add(new CoinPile("3,8"));
            coinpiles.Add(new CoinPile("2,9"));
            healthpacks.Add(new HealthPack("1,5"));
            healthpacks.Add(new HealthPack("8,7"));
            healthpacks.Add(new HealthPack("2,5"));
            //bullets.Add(new Bullet("9,9", 0));
            bullets.Add(new Bullet("19,3", 3));
            //bullets.Add(new Bullet("9,9", 2));
            //bullets.Add(new Bullet("19,19", 0));
            bricks.Add(new Brick("4,15,0"));
            bricks.Add(new Brick("5,15,1"));
            bricks.Add(new Brick("6,15,2"));
            bricks.Add(new Brick("7,15,3"));
            bricks.Add(new Brick("8,15,4"));
            t[0] = new Tank(1, new Point(0, 0), 0);
            t[1] = new Tank(2, new Point(1, 1), 1);
            t[2] = new Tank(3, new Point(2, 2), 2);
            t[3] = new Tank(4, new Point(3, 3), 3);
        }

        /// <summary>
        /// UnloadContent will be called once per game and is the place to unload
        /// all content.
        /// </summary>
        protected override void UnloadContent()
        {
            // TODO: Unload any non ContentManager content here
        }

        /// <summary>
        /// Allows the game to run logic such as updating the world,
        /// checking for collisions, gathering input, and playing audio.
        /// </summary>
        /// <param name="gameTime">Provides a snapshot of timing values.</param>
        protected override void Update(GameTime gameTime)
        {
            // Allows the game to exit
            if (GamePad.GetState(PlayerIndex.One).Buttons.Back == ButtonState.Pressed)
                this.Exit();
            updateBullets();
            // TODO: Add your update logic here
            checkCollisions();
            base.Update(gameTime);
        }

        private void checkCollisions()
        {
            for (int i = 0; i < bullets.Count; i++)
            {
                Bullet b = bullets.ElementAt(i);
                //bullet on tanks
                for (int j = 0; j < t.Length && t[j] != null; j++)
                {
                    Rectangle region = GetBounds(t[j].getPoint());
                    Console.WriteLine(region.ToString() + "  " + (int)b.getPos().X+" " + (int)b.getPos().Y);
                    if (region.Contains(new Point((int)b.getPos().X,(int)b.getPos().Y)))
                    {
                        bullets.RemoveAt(i);
                        i--;
                    }
                }

                //bullet on bricks
                //bullet on stones
            }
        }

        private void updateBullets()
        {
            for (int i = 0; i < bullets.Count; i++)
            {
                Bullet b = bullets.ElementAt(i);
                Vector2 p = b.getPos();
                switch (b.getDirection())
                {
                    case 3:
                        p += new Vector2(-Constants.SPEED, 0);
                        break;
                    case 2:
                        p += new Vector2(0, Constants.SPEED);
                        break;
                    case 1:
                        p += new Vector2(Constants.SPEED, 0);
                        break;
                    case 0:
                        p += new Vector2(0, -Constants.SPEED);
                        break;
                }
                if (p.X < 30 || p.Y < 30 || p.X > (30 + Constants.SQUARESIZE * Constants.GRID_SIZE)
                    || p.Y > (30 + Constants.SQUARESIZE * Constants.GRID_SIZE))
                {
                    bullets.RemoveAt(i);
                    i--;
                }
                else
                {
                    b.update(p);
                }
            }
        }

        private void drawBoard()
        {
            for (int i = 0; i < Constants.GRID_SIZE; i++)
            {
                for (int j = 0; j < Constants.GRID_SIZE; j++)
                {
                    spriteBatch.Draw(sandTexture, tiles[i, j], Color.White);
                }
            }
        }

        /// <summary>
        /// This is called when the game should draw itself.
        /// </summary>
        /// <param name="gameTime">Provides a snapshot of timing values.</param>
        protected override void Draw(GameTime gameTime)
        {
            GraphicsDevice.Clear(Color.CornflowerBlue);
            spriteBatch.Begin();
            drawScenery();
            drawBoard();
            drawObjects(stoneobs, stoneTexture);
            drawObjects(waterobs, waterTexture);
            drawhealthPacks(healthpacks);
            drawCoinPiles(coinpiles);
            drawBricks();
            drawBullets();
            drawTanks();
            DrawText();
            spriteBatch.End();
            // TODO: Add your drawing code here
            base.Draw(gameTime);
        }

        private void drawBullets()
        {
            foreach (Bullet b in bullets)
            {
                spriteBatch.Draw(bulletTexture, b.getPos(), null, Color.White, b.getRotation(),
                    new Vector2(42, 240), 0.1f, SpriteEffects.None, 1);
            }
        }

        private void drawTanks()
        {
            for (int i = 0; i < t.Length && t[i] != null; i++)
            {
                spriteBatch.Draw(tankTexture, t[i].getPos(), null, Color.White, t[i].getRotation(),
                       t[i].locate(), 0.5f, SpriteEffects.None, 1);
            }
        }

        private void drawBricks()
        {
            foreach (Brick b in bricks)
            {
                spriteBatch.Draw(brickTexture, tiles[b.getPos().X, b.getPos().Y],
                    Color.White * ((4 - b.getDamage()) / 4.0f));
            }
        }

        private void drawScenery()
        {
            Rectangle screenRectangle = new Rectangle(0, 0, screenWidth, screenHeight);
            spriteBatch.Draw(backgroundTexture, screenRectangle, Color.White);
        }

        private void drawhealthPacks(List<HealthPack> healthpacks)
        {
            foreach (HealthPack o in healthpacks)
            {
                spriteBatch.Draw(healthPackTexture, tiles[o.getPos().X, o.getPos().Y], Color.White);
            }
        }

        private void drawCoinPiles(List<CoinPile> coinpiles)
        {
            foreach (CoinPile o in coinpiles)
            {
                spriteBatch.Draw(coinPileTexture, tiles[o.getPos().X, o.getPos().Y], Color.White);
            }
        }

        private void drawObjects(List<Obstacle> obs, Texture2D t)
        {
            foreach (Obstacle o in obs)
            {
                spriteBatch.Draw(t, tiles[o.position().X, o.position().Y], Color.White);
            }
        }

        private void DrawText()
        {
            spriteBatch.Draw(scoreCardTexture, new Rectangle(Constants.GRID_SIZE * Constants.SQUARESIZE + 50,
                50, 340, 240), Color.White);
            int x = 0;
            spriteBatch.DrawString(font, "Player ", new Vector2(Constants.GRID_SIZE * Constants.SQUARESIZE + 55,
                    60 + x), Color.Black);
            spriteBatch.DrawString(font, "Points ", new Vector2(Constants.GRID_SIZE * Constants.SQUARESIZE + 135,
            60 + x), Color.Black);
            spriteBatch.DrawString(font, "Coins ", new Vector2(Constants.GRID_SIZE * Constants.SQUARESIZE + 195,
                                60 + x), Color.Black);
            spriteBatch.DrawString(font, "Health ", new Vector2(Constants.GRID_SIZE * Constants.SQUARESIZE + 265,
                                60 + x), Color.Black);
            spriteBatch.DrawString(font, "Rank ", new Vector2(Constants.GRID_SIZE * Constants.SQUARESIZE + 335,
                                60 + x), Color.Black);
            x += 40;
            for (int i = 0; i < 5; i++)
            {
                spriteBatch.DrawString(font, "Player " + i, new Vector2(Constants.GRID_SIZE * Constants.SQUARESIZE + 55,
                60 + x), Color.Black);
                spriteBatch.DrawString(font, "Points ", new Vector2(Constants.GRID_SIZE * Constants.SQUARESIZE + 135,
                60 + x), Color.Black);
                spriteBatch.DrawString(font, "Coins ", new Vector2(Constants.GRID_SIZE * Constants.SQUARESIZE + 195,
                                    60 + x), Color.Black);
                spriteBatch.DrawString(font, "Health ", new Vector2(Constants.GRID_SIZE * Constants.SQUARESIZE + 265,
                                    60 + x), Color.Black);
                spriteBatch.DrawString(font, "Rank ", new Vector2(Constants.GRID_SIZE * Constants.SQUARESIZE + 335,
                                    60 + x), Color.Black);

                x += 40;
            }
        }

        public static Rectangle GetBounds(Point p)
        {
            return new Rectangle(p.X * Constants.SQUARESIZE + 30, p.Y * Constants.SQUARESIZE + 30,
                Constants.SQUARESIZE, Constants.SQUARESIZE);
        }
    }
}
