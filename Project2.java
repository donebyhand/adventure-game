import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Project2 {
	public static void main(String[] args) {

//Welcome Message
System.out.println("Do you want to play a game? ..." + "\n" + 
	"Well you have no choice but to say yes." + "\n" + 
	"Welcome to \"Not Fallout New Vegas\", a game where you, the player, are given the chance to conquer your enemies." + "\n");

Scanner scanner = new Scanner(System.in);

// og seed generator
long seed = 1731469587184L;
Random gen = new Random(seed);
System.out.println("Current seed is " + seed);

//Player chooses gamemode
System.out.print("Which game mode would you like to play? (0 for Standard, 1 for Survival): ");
int gamemode = scanner.nextInt();


//Player Name
System.out.print("What is your character's name? ");
String playerName = scanner.next();

//Declaring these outside my loops
int playerHealth, playerAttack, playerArmor;

//Player Health betweetn 0 and 1000
while (true) {
System.out.print("How much health does " + playerName + " have? ");
	playerHealth = scanner.nextInt();
	if (playerHealth >= 0 && playerHealth <=1000) {
		break;
	} else {
		System.out.print("Health must be between 0 and 1000, please try again. ");
	}
}

//Player Attack between 5 and 15
while (true) {
System.out.print("How much attack does " + playerName + " have? ");
	playerAttack = scanner.nextInt();
	if (playerAttack >=5 && playerAttack <= 15) {
		break;
	} else {
		System.out.print("Attack power must be between 5 and 15, please try again. ");
	}
}

//Player Armor between 3 and 5
while (true) {
System.out.print("How much armor does " + playerName + " have? ");
	playerArmor = scanner.nextInt();
	if (playerArmor >=3 && playerArmor <=5) {
		break;
	} else {
		System.out.print("Armor level must be between 3 and 5, please try again. ");
	}
}

//Print Stats
System.out.println(playerName + " has " + playerHealth + " health, " + playerAttack + " attack, and " + playerArmor + " armor. ");

//Print game mode
String modeResult = "";

if (gamemode == 0) {
	 modeResult = "Standard";
} else if (gamemode == 1) {
	modeResult = "Survival";
}

System.out.println("You have selected " + modeResult + " mode!" + "\n");

//Print difficulty choice if Survival is chosen and enemy amount depending on mode
String diffResult = "";
int enemyAmount = 0;

if (gamemode == 1) {
	System.out.print("Which difficulty would you like to play? (0 for Easy, 1 for Normal, 2 for Random): ");
	int gamedifficult = scanner.nextInt();

	if (gamedifficult == 0) {
	 	diffResult = "Easy";
	 	enemyAmount = 3;
	} else if (gamedifficult == 1) {
		diffResult = "Normal";
		enemyAmount = 5;
	} else if (gamedifficult == 2) {
		diffResult = "Random";
		enemyAmount = 7;
	} 

System.out.println("You have selected " + diffResult + "!" + "\n");

} else if (gamemode == 0) {
	enemyAmount = 3;

} //end bracket of survival mode (making note so I remember)

//Filler line? shown in sample output
System.out.println("---------------------------" + "\n");

//for storing our enermies then prompting the user to name them
ArrayList<String> enemyNames = new ArrayList<>();
ArrayList<Integer> enemyHealth = new ArrayList<>();
ArrayList<Integer> enemyAttack = new ArrayList<>();
ArrayList<Integer> enemyArmor = new ArrayList<>();

for (int i=1; i <= enemyAmount; i++) {
	System.out.print("What is enemy " + i + "'s name? ");
	String enemyName = scanner.next();
	enemyNames.add(enemyName);

	int enHealth = gen.nextInt(11)+5;
	int enAttack = gen.nextInt(6)+5;
	int enArmor = gen.nextInt(3)+1;

	enemyHealth.add(enHealth);
	enemyAttack.add(enAttack);
	enemyArmor.add(enArmor);
}

System.out.println(enemyNames);
System.out.println(enemyHealth);
System.out.println(enemyAttack);
System.out.println(enemyArmor);

int round = 1;
scanner.close();

//STANDARD MODE!!!! Just a neater version of Project 1 except the player inputs the player's name and stats from above
if (gamemode == 0) {
	int playerScore = 0;
while (playerHealth > 0) {
	int roundScore = 0;
	System.out.println("\n" + "+++++++++++++++++++++++++++++" + "\n" +
						"+++++++++++++++++++++++++++++" + "\n" +
						"++++++++++ ROUND " + round + " ++++++++++" + "\n" +
						"+++++++++++++++++++++++++++++" + "\n" +
						"+++++++++++++++++++++++++++++" + "\n");
	System.out.println("---------- " + playerName + " goes first! ----------" + "\n");

		for (int i=0; i<enemyAmount; i++) {
			System.out.println(playerName + " [" + playerAttack + " attack] is attacking " + enemyNames.get(i) + " [" + enemyHealth.get(i) + " health, " + enemyArmor.get(i) + " armor]" + "\n");

				int enemyDamage = playerAttack - enemyArmor.get(i);
				int newEnemyHealth = enemyHealth.get(i) - enemyDamage;
				enemyHealth.set(i, newEnemyHealth);
				System.out.println(enemyNames.get(i) + " now has a health level of " + newEnemyHealth);

				roundScore += enemyDamage;
				System.out.println("Score +" + enemyDamage + "!" + "\n");

						if (newEnemyHealth <= 0) {
						System.out.println(enemyNames.get(i) + " has been defeated!");
						roundScore += 100;
						System.out.println("Score +100!" + "\n");
						}
				}

				System.out.println("---------- " + playerName + "'s turn ends. ----------" + "\n" + "\n" + "Score: " + playerScore + "\n");

	System.out.println("---------- Enemy Turn Begins ----------" + "\n");
	
		for (int i=0; i<enemyAmount; i++){
			System.out.println(enemyNames.get(i) + " [" + enemyAttack.get(i) + " attack] is attacking " + playerName + " [" + playerHealth + " health, " + playerArmor + " armor]");

			int playerDamage = enemyAttack.get(i) - playerArmor;
				playerHealth -= playerDamage;
			System.out.println(playerName + "'s health is now " + playerHealth + "\n");

				if (playerHealth <= 0) {
					System.out.println("YOU DIED, GAME OVER!" + "\n");
					break;
				}

		} 	

		System.out.println("---------- Enemy turn ends ----------" + "\n");


		System.out.println("Round Score: " + roundScore);
					int roundComplete = (int)(roundScore * 0.15);
				System.out.println("Bonus: " + roundComplete);
					playerScore += roundScore + roundComplete;
				System.out.println("Total Score: " + playerScore + "\n");

	if (playerHealth>0) {
		round++;
		}

	}

} //End Standard Mode bracket here

//Easy Mode time
if (gamemode == 1 && diffResult == "Easy") {
	int playerScore = 0;
while (playerHealth > 0) {
	int roundScore = 0;
	System.out.println("\n" + "+++++++++++++++++++++++++++++" + "\n" +
						"+++++++++++++++++++++++++++++" + "\n" +
						"++++++++++ ROUND " + round + " ++++++++++" + "\n" +
						"+++++++++++++++++++++++++++++" + "\n" +
						"+++++++++++++++++++++++++++++" + "\n");
	System.out.println("---------- " + playerName + " goes first! ----------" + "\n");

		for (int i=0; i<enemyAmount; i++) {
			System.out.println(playerName + " [" + playerAttack + " attack] is attacking " + enemyNames.get(i) + " [" + enemyHealth.get(i) + " health, " + enemyArmor.get(i) + " armor]" + "\n");

				int enemyDamage = playerAttack - enemyArmor.get(i);
				int newEnemyHealth = enemyHealth.get(i) - enemyDamage;
				enemyHealth.set(i, newEnemyHealth);
				System.out.println(enemyNames.get(i) + " now has a health level of " + newEnemyHealth);

				roundScore += enemyDamage;
				System.out.println("Score +" + enemyDamage + "!" + "\n");

						if (newEnemyHealth <= 0) {
						System.out.println(enemyNames.get(i) + " has been defeated!");
						roundScore += 100;
						System.out.println("Score +100!" + "\n");
						}
				}

	System.out.println("---------- " + playerName + "'s turn ends. ----------" + "\n" + "\n" + "Score: " + playerScore + "\n");


	System.out.println("---------- Enemy Turn Begins ----------" + "\n");
	
		for (int i=0; i<enemyAmount; i++){
			System.out.println(enemyNames.get(i) + " [" + enemyAttack.get(i) + " attack] is attacking " + playerName + " [" + playerHealth + " health, " + playerArmor + " armor]");

			int playerDamage = enemyAttack.get(i) - playerArmor;
				playerHealth -= playerDamage;
			System.out.println(playerName + "'s health is now " + playerHealth + "\n");

				if (playerHealth <= 0) {
					System.out.println("YOU DIED, GAME OVER!" + "\n");
					break;
				}
		} 	

		playerAttack += 2;

		System.out.println("---------- Enemy turn ends ----------" + "\n");


		System.out.println("Round Score: " + roundScore);
					int roundComplete = (int)(roundScore * 0.15);
				System.out.println("Bonus: " + roundComplete);
					playerScore += roundScore + roundComplete;
				System.out.println("Total Score: " + playerScore + "\n");

		if (playerHealth>0) {
		round++;
		}
	}

} //End of easy mode bracket

//Normal Mode begins
if (gamemode == 1 && diffResult == "Normal") {
	int playerScore = 0;
while (playerHealth > 0) {
	int roundScore = 0;
	System.out.println("\n" + "+++++++++++++++++++++++++++++" + "\n" +
						"+++++++++++++++++++++++++++++" + "\n" +
						"++++++++++ ROUND " + round + " ++++++++++" + "\n" +
						"+++++++++++++++++++++++++++++" + "\n" +
						"+++++++++++++++++++++++++++++" + "\n");
	System.out.println("---------- " + playerName + " goes first! ----------" + "\n");

		for (int i=0; i<enemyAmount; i++) {
			System.out.println(playerName + " [" + playerAttack + " attack] is attacking " + enemyNames.get(i) + " [" + enemyHealth.get(i) + " health, " + enemyArmor.get(i) + " armor]" + "\n");

				int enemyDamage = playerAttack - enemyArmor.get(i);
				int newEnemyHealth = enemyHealth.get(i) - enemyDamage;
				enemyHealth.set(i, newEnemyHealth);
				System.out.println(enemyNames.get(i) + " now has a health level of " + newEnemyHealth);

				roundScore += enemyDamage;
				System.out.println("Score +" + enemyDamage + "!" + "\n");

						if (newEnemyHealth <= 0) {
						System.out.println(enemyNames.get(i) + " has been defeated!");
						roundScore += 100;
						System.out.println("Score +100!" + "\n");
						}
				}

				System.out.println("---------- " + playerName + "'s turn ends. ----------" + "\n" + "\n" + "Score: " + playerScore + "\n");
					

	System.out.println("---------- Enemy Turn Begins ----------" + "\n");
	
		for (int i=0; i<enemyAmount; i++){
			System.out.println(enemyNames.get(i) + " [" + enemyAttack.get(i) + " attack] is attacking " + playerName + " [" + playerHealth + " health, " + playerArmor + " armor]");

			int playerDamage = enemyAttack.get(i) - playerArmor;
				playerHealth -= playerDamage;
			System.out.println(playerName + "'s health is now " + playerHealth + "\n");

				if (playerHealth <= 0) {
					System.out.println("YOU DIED, GAME OVER!" + "\n");
					break;
				}
		} 

		playerAttack += 1;
		
		for (int i=0; i<enemyAmount; i++){
			enemyArmor.set(i, enemyArmor.get(i) + 1);
			enemyAttack.set(i, enemyAttack.get(i) +1);
		}

		System.out.println("---------- Enemy turn ends ----------" + "\n");

		System.out.println("Round Score: " + roundScore);
					int roundComplete = (int)(roundScore * 0.15);
				System.out.println("Bonus: " + roundComplete);
					playerScore += roundScore + roundComplete;
				System.out.println("Total Score: " + playerScore + "\n");


		if (playerHealth>0) {
		round++;
	}

}

} // End Normal Mode bracket here

//Begin Random Mode (the scary one)
if (gamemode == 1 && diffResult == "Random") {
	int playerScore = 0;
	int currentPlayerHealth = playerHealth;
	Random gen2 = new Random();

while (playerHealth > 0) {
	int roundScore = 0;
	System.out.println("\n" + "+++++++++++++++++++++++++++++" + "\n" +
						"+++++++++++++++++++++++++++++" + "\n" +
						"++++++++++ ROUND " + round + " ++++++++++" + "\n" +
						"+++++++++++++++++++++++++++++" + "\n" +
						"+++++++++++++++++++++++++++++" + "\n");

	//Coin Flip - who goes first
	boolean playerFirst = gen2.nextBoolean();
	System.out.println("According to a coin flip, " + (playerFirst ? playerName + " goes first!" : "Enemy goes first!")+ "\n");

	//Dice roll fate...
	int diceroll = gen2.nextInt(6)+1;
	System.out.println("You rolled a " + diceroll);

	switch (diceroll) {
		case 1:
			playerHealth /= 2;
				if (playerHealth < 0) {
					playerHealth = 0;
				}
			System.out.println(playerName + "'s health has been halved to " + playerHealth +"\n");
				break;

		case 2:
			playerHealth += 100;
			if (playerHealth > 900){
				playerHealth = 900;
			}
			System.out.println(playerName + " recovers 100 health and now has " + playerHealth + " health" + "\n");
				break;

		case 3:
			for (int i=0; i<enemyAmount; i++) {
				enemyAttack.set(i, enemyAttack.get(i)*2);
				enemyArmor.set(i, enemyArmor.get(i)*2);
			}
			System.out.println("Enemy attack power and armor level are doubled for this round..." + "\n");
				break;

		case 4:
			playerAttack *= 2;
			playerArmor *= 2;
			System.out.println(playerName + "'s attack power and armor level are doubled for this round!" + "\n");
				break;

		case 5:
			playerAttack /= 2;
			playerArmor /= 2;
			System.out.println(playerName + "'s attack power and armor level are halved for this round..." + "\n");
				break;

		case 6:
			System.out.println(playerName + " defeats all enemies this round!" + "\n");
			for (int i=0; i<enemyAmount; i++){
				enemyHealth.set(i, 0);
			}
			break;
	}

		for (int i=0; i<enemyAmount; i++) {
			int randomLevels = gen2.nextInt(5)-2;

			int newAttack = enemyAttack.get(i) + randomLevels;
				if (newAttack < 0) {
					newAttack = 0;
				}
			enemyAttack.set(i, newAttack);

			int newArmor = enemyArmor.get(i) + randomLevels;
				if (newArmor < 0) {
					newArmor = 0;
				}
			enemyArmor.set(i, newArmor);
		}


	if (playerFirst) {

	System.out.println("---------- " + playerName + " goes first! ----------" + "\n");

		for (int i=0; i < enemyAmount; i++) {
			System.out.println(playerName + " [" + playerAttack + " attack] is attacking " + enemyNames.get(i) + " [" + enemyHealth.get(i) + " health, " + enemyArmor.get(i) + " armor]" + "\n");

				int enemyDamage = playerAttack - enemyArmor.get(i);
				int newEnemyHealth = enemyHealth.get(i) - enemyDamage;
				enemyHealth.set(i, newEnemyHealth);
				System.out.println(enemyNames.get(i) + " now has a health level of " + newEnemyHealth);

				roundScore += enemyDamage;
				System.out.println("Score +" + enemyDamage + "!" + "\n");

					if (newEnemyHealth <= 0) {
						System.out.println(enemyNames.get(i) + " has been defeated!");
						roundScore += 100;
						System.out.println("Score +100!" + "\n");
						}
				}

			System.out.println("---------- " + playerName + "'s turn ends. ----------" + "\n" + "\n" + "Score: " + playerScore + "\n");

		System.out.println("---------- Enemy Turn Begins ----------" + "\n");
	
		for (int i=0; i<enemyAmount; i++){
			System.out.println(enemyNames.get(i) + " [" + enemyAttack.get(i) + " attack] is attacking " + playerName + " [" + playerHealth + " health, " + playerArmor + " armor]");

			int playerDamage = enemyAttack.get(i) - playerArmor;
				playerHealth -= playerDamage;
			System.out.println(playerName + "'s health is now " + playerHealth + "\n");

				if (playerHealth <= 0) {
					System.out.println("YOU DIED, GAME OVER!" + "\n");
					break;
				}
		} 

		System.out.println("---------- Enemy turn ends ----------" + "\n");
	}
					
	if (!playerFirst) {
		System.out.println("---------- Enemy Turn Begins ----------" + "\n");
	
		for (int i=0; i<enemyAmount; i++){
			System.out.println(enemyNames.get(i) + " [" + enemyAttack.get(i) + " attack] is attacking " + playerName + " [" + playerHealth + " health, " + playerArmor + " armor]");

			int playerDamage = enemyAttack.get(i) - playerArmor;
			playerHealth -= playerDamage;
			System.out.println(playerName + "'s health is now " + playerHealth + "\n");

				if (playerHealth <= 0) {
					System.out.println("YOU DIED, GAME OVER!" + "\n");
					break;
				}
			} 
		System.out.println("---------- Enemy turn ends ----------" + "\n");

		System.out.println("---------- " + playerName + "'s turn begins. ----------" + "\n");

		for (int i=0; i<enemyAmount; i++) {
			System.out.println(playerName + " [" + playerAttack + " attack] is attacking " + enemyNames.get(i) + " [" + enemyHealth.get(i) + " health, " + enemyArmor.get(i) + " armor]" + "\n");

				int enemyDamage = playerAttack - enemyArmor.get(i);
				int newEnemyHealth = enemyHealth.get(i) - enemyDamage;
				enemyHealth.set(i, newEnemyHealth);
				System.out.println(enemyNames.get(i) + " now has a health level of " + newEnemyHealth);

				roundScore += enemyDamage;
				System.out.println("Score +" + enemyDamage + "!" + "\n");

						if (newEnemyHealth <= 0) {
						System.out.println(enemyNames.get(i) + " has been defeated!");
						roundScore += 100;
						System.out.println("Score +100!" + "\n");
						}
				}

			playerAttack += 1;

			System.out.println("---------- " + playerName + "'s turn ends. ----------");
	}

		System.out.println("Round Score: " + roundScore);
					int roundComplete = (int)(roundScore * 0.15);
				System.out.println("Bonus: " + roundComplete);
					playerScore += roundScore + roundComplete;
				System.out.println("Total Score: " + playerScore + "\n");

							if (playerHealth>0) {
							round++;
						}

					}

} // End Random mode bracket here YYAYY!

	System.out.println("The end came too soon... but " + playerName + " survived " + round + " rounds." + "\n");

	}
}