package Card;


public class Factory {
	
	
	public static void main(String[] args){
		
		
		// THe following factory is for deck 1
		
		
		
		
		
		
	
if(pokemonName=="DoduoGEN")
{
	int[] retreatEnergyCost = new int[11];
	retreatEnergyCost  = PokemonCard.setAndReturnEnergyArray(1,0,0,0,0,0,0,0,0,0,0);

	List<Ability> abilities = new ArrayList<>();
	AbilitiesFactory abilitiesFactory = new AbilitiesFactory();
	abilities.add(AbilitiesFactory.getAbility("Double Stab"));//10 damage
	abilities.add(AbilitiesFactory.getAbility("DoDuo Delivery"));

                  //weakness Lightning;
	             //resistance Fighting;
	PokemonCard DoduoGEN = new PokemonCard ("Basic", "Dodugo", 60, abilities  , retreatEnergyCost);
}

else if(pokemonName=="DodrioGEN")
{
	int[] retreatEnergyCost = new int[11];
	retreatEnergyCost  = PokemonCard.setAndReturnEnergyArray(1,0,0,0,0,0,0,0,0,0,0);

	List<Ability> abilities = new ArrayList<>();
	AbilitiesFactory abilitiesFactory = new AbilitiesFactory();
	abilities.add(AbilitiesFactory.getAbility("Fury Attack"));// 40 damage
	                  //weakness Lightning;
                     //resistance Fighting;
	PokemonCard DodrioGEN = new PokemonCard ("Stage-1", "Dodrio", 90, abilities  , retreatEnergyCost);
	setEvolvesFrom();
}

else if(pokemonName=="MeowthAOR")
{
	int[] retreatEnergyCost = new int[11];
	retreatEnergyCost  = PokemonCard.setAndReturnEnergyArray(1,0,0,0,0,0,0,0,0,0,0);

	List<Ability> abilities = new ArrayList<>();
	AbilitiesFactory abilitiesFactory = new AbilitiesFactory();
	abilities.add(AbilitiesFactory.getAbility("Act Tough")); //10 damage, if any dark card is attached to it, 20 damage will be done to the opponent pokemon
	                  //weakness Fighting;
                     //resistance ***;

	PokemonCard MeowthAOR = new PokemonCard ("Basic", "Meowth", 60, abilities  , retreatEnergyCost);
}


else if(pokemonName=="PersianGEN")
{
	int[] retreatEnergyCost = new int[11];
	retreatEnergyCost  = PokemonCard.setAndReturnEnergyArray(1,0,0,0,0,0,0,0,0,0,0);

	List<Ability> abilities = new ArrayList<>();
	AbilitiesFactory abilitiesFactory = new AbilitiesFactory();
	abilities.add(AbilitiesFactory.getAbility("Fake Out"));// 30 damage
	abilities.add(AbilitiesFactory.getAbility("Ambush"));//40 damage
	                  //weakness Fighting;
                     //resistance ****;

	PokemonCard PersianGEN = new PokemonCard ("Stage-1", "Persian", 90, abilities  , retreatEnergyCost);
	setEvolvesFrom();
}


else if(pokemonName=="DiglettGEN")
{
	int[] retreatEnergyCost = new int[11];
	retreatEnergyCost  = PokemonCard.setAndReturnEnergyArray(0,0,0,0,0,0,0,0,0,1,0);

	List<Ability> abilities = new ArrayList<>();
	AbilitiesFactory abilitiesFactory = new AbilitiesFactory();
	abilities.add(AbilitiesFactory.getAbility("Mine Look"));// which shuffles the opponents deck
	abilities.add(AbilitiesFactory.getAbility("Mud slap"));//20 damage
	                  //weakness Grass;
                     //resistance ****;
	PokemonCard DiglettGEN = new PokemonCard ("Basic", "Diglett", 50, abilities  , retreatEnergyCost);
}


else if(pokemonName=="DugtrioGEN")
{
	int[] retreatEnergyCost = new int[11];
	retreatEnergyCost  = PokemonCard.setAndReturnEnergyArray(0,0,0,0,0,0,0,0,0,1,0);

	List<Ability> abilities = new ArrayList<>();
	AbilitiesFactory abilitiesFactory = new AbilitiesFactory();
	abilities.add(AbilitiesFactory.getAbility("Earthquake"));// does 10 damage to all the pokemons on the bench
	abilities.add(AbilitiesFactory.getAbility("Rock Rumble"));//60 damage
	                  //weakness Grass;
                     //resistance ****;

	PokemonCard DugtrioGEN = new PokemonCard ("Stage-1", "Dugtrio", 90, abilities  , retreatEnergyCost);
	setEvolvesFrom();
}


else if(pokemonName=="GeodudeGEN")
{
	int[] retreatEnergyCost = new int[11];
	retreatEnergyCost  = PokemonCard.setAndReturnEnergyArray(0,0,0,0,0,0,0,0,0,2,0);

	List<Ability> abilities = new ArrayList<>();
	AbilitiesFactory abilitiesFactory = new AbilitiesFactory();
	abilities.add(AbilitiesFactory.getAbility("Roll Out"));// 10 damage
	abilities.add(AbilitiesFactory.getAbility("Flail"));//10 times the number of damage counters on this pokemon
	                  //weakness Grass;
                     //resistance ****;
	PokemonCard GeodudeGEN = new PokemonCard ("Basic", "Geodude", 50, abilities  , retreatEnergyCost);
}


else if(pokemonName=="HitmonchanGEN")
{
	int[] retreatEnergyCost = new int[11];
	retreatEnergyCost  = PokemonCard.setAndReturnEnergyArray(0,0,0,0,0,0,0,0,0,1,0);

	List<Ability> abilities = new ArrayList<>();
	AbilitiesFactory abilitiesFactory = new AbilitiesFactory();
	abilities.add(AbilitiesFactory.getAbility("Bullet Punch"));// flip coin for 2 times ......does 20 damage for each head
	abilities.add(AbilitiesFactory.getAbility("Mach Cross"));//60 damage
	                  //weakness Psychic;
                     //resistance ****;
	PokemonCard HitmonchanGEN = new PokemonCard ("Basic", "Hitmonchan", 50, abilities  , retreatEnergyCost);
}



else if(pokemonName=="HitmonleeGEN")
{
	int[] retreatEnergyCost = new int[11];
	retreatEnergyCost  = PokemonCard.setAndReturnEnergyArray(0,0,0,0,0,0,0,0,0,1,0);

	List<Ability> abilities = new ArrayList<>();
	AbilitiesFactory abilitiesFactory = new AbilitiesFactory();
	abilities.add(AbilitiesFactory.getAbility("Stretch Kick"));// does 30 damage to one of opponents bench pokemon
	abilities.add(AbilitiesFactory.getAbility("Spiral Kick"));//30 damage
	                  //weakness Psychic;
                     //resistance ****;
	PokemonCard HitmonleeGEN = new PokemonCard ("Stage-1", "Hitmonlee", 90, abilities  , retreatEnergyCost);
	setEvolvesFrom();
}


else if(pokemonName=="MachopGEN")
{
	int[] retreatEnergyCost = new int[11];
	retreatEnergyCost  = PokemonCard.setAndReturnEnergyArray(0,0,0,0,0,0,0,0,0,2,0);

	List<Ability> abilities = new ArrayList<>();
	AbilitiesFactory abilitiesFactory = new AbilitiesFactory();
	abilities.add(AbilitiesFactory.getAbility("Knuckle Punch"));// 10 damage
	                  //weakness Psychic;
                     //resistance ****;
	PokemonCard MachopGEN = new PokemonCard ("Basic", "Machop", 70, abilities  , retreatEnergyCost);
}

else if(pokemonName=="MachokeGEN")
{
	int[] retreatEnergyCost = new int[11];
	retreatEnergyCost  = PokemonCard.setAndReturnEnergyArray(0,0,0,0,0,0,0,0,0,2,0);

	List<Ability> abilities = new ArrayList<>();
	AbilitiesFactory abilitiesFactory = new AbilitiesFactory();
	abilities.add(AbilitiesFactory.getAbility("Beat Down"));// 40 damage
	                  //weakness Psychic;
                     //resistance ****;
	PokemonCard MachokeGEN = new PokemonCard ("Stage-1", "Machoke", 90, abilities  , retreatEnergyCost);
	setEvolvesFrom();
}

else if(pokemonName=="EspurrGEN")
{
	int[] retreatEnergyCost = new int[11];
	retreatEnergyCost  = PokemonCard.setAndReturnEnergyArray(0,0,0,0,1,0,0,0,0,0,0);

	List<Ability> abilities = new ArrayList<>();
	AbilitiesFactory abilitiesFactory = new AbilitiesFactory();
	abilities.add(AbilitiesFactory.getAbility("Sleep"));// put the opponent pokemon in sleep
	                  //weakness Psychic;
                     //resistance ****;
	PokemonCard EspurrGEN = new PokemonCard ("Basic", "Espurr", 50, abilities  , retreatEnergyCost);
}

else if(pokemonName=="MeowsticGEN")
{
	int[] retreatEnergyCost = new int[11];
	retreatEnergyCost  = PokemonCard.setAndReturnEnergyArray(0,0,0,0,1,0,0,0,0,0,0);

	List<Ability> abilities = new ArrayList<>();
	AbilitiesFactory abilitiesFactory = new AbilitiesFactory();
	abilities.add(AbilitiesFactory.getAbility("Ear Influence"));// you can do as much of damage as you want from whatever pokemon on the opponent's bench
	abilities.add(AbilitiesFactory.getAbility("Psychic"));// 60 damage does 10 more damage to every energy card your opponent attach to their pokemon
	                  //weakness Psychic;
                     //resistance ****;
	PokemonCard MeowsticGEN = new PokemonCard ("Stage-1", "Meowstic", 90, abilities  , retreatEnergyCost);
	setEvolvesFrom();
}



else if(pokemonName=="GastlyGEN")
{
	int[] retreatEnergyCost = new int[11];
	retreatEnergyCost  = PokemonCard.setAndReturnEnergyArray(0,0,0,0,1,0,0,0,0,0,0);

	List<Ability> abilities = new ArrayList<>();
	AbilitiesFactory abilitiesFactory = new AbilitiesFactory();
	abilities.add(AbilitiesFactory.getAbility("Sleep Poison"));// Flip a coin if heads opponents pokemon goes to sleep
	                  //weakness Darkness;
                     //resistance Fighting;
	PokemonCard GastlyGEN = new PokemonCard ("Basic", "Meowstic", 50, abilities  , retreatEnergyCost);
}

else if(pokemonName=="HaunterGEN")
{
	int[] retreatEnergyCost = new int[11];
	retreatEnergyCost  = PokemonCard.setAndReturnEnergyArray(0,0,0,0,1,0,0,0,0,0,0);

	List<Ability> abilities = new ArrayList<>();
	AbilitiesFactory abilitiesFactory = new AbilitiesFactory();
	abilities.add(AbilitiesFactory.getAbility("Poison Ring"));// your opponrns pokemon is being poisoned and it cant be retreated on its turn
	
	                  //weakness Darkness;
                     //resistance Fighting;
	PokemonCard HaunterGEN = new PokemonCard ("Stage-1", "Haunter", 70, abilities  , retreatEnergyCost);
	setEvolvesFrom();
}


else if(pokemonName=="JirachiGEN")
{
	int[] retreatEnergyCost = new int[11];
	retreatEnergyCost  = PokemonCard.setAndReturnEnergyArray(0,0,0,0,1,0,0,0,0,0,0);

	List<Ability> abilities = new ArrayList<>();
	AbilitiesFactory abilitiesFactory = new AbilitiesFactory();
	abilities.add(AbilitiesFactory.getAbility("Wish"));// search your deck for a card and put it in your hand and shuffle your deck afterwords
	abilities.add(AbilitiesFactory.getAbility("Heart Sing"));// 50 damage
	                  //weakness Psychic;
                     //resistance ****;
	PokemonCard JirachiGEN = new PokemonCard ("Basic", "Jirachi", 70, abilities  , retreatEnergyCost);
}


else if(pokemonName=="JynxFFI")
{
	int[] retreatEnergyCost = new int[11];
	retreatEnergyCost  = PokemonCard.setAndReturnEnergyArray(0,0,0,0,1,0,0,0,0,0,0);

	List<Ability> abilities = new ArrayList<>();
	AbilitiesFactory abilitiesFactory = new AbilitiesFactory();
	abilities.add(AbilitiesFactory.getAbility("victory Kiss"));// you can do as much of damage as you want from whatever pokemon on the opponent's bench
	abilities.add(AbilitiesFactory.getAbility("Hug"));// 30 damage
	                  //weakness Psychic;
                     //resistance ****;
	PokemonCard JynxFFI = new PokemonCard ("Basic", "Jynx", 70, abilities  , retreatEnergyCost);
	
}


else if(pokemonName=="SlowpokeGEN")
{
	int[] retreatEnergyCost = new int[11];
	retreatEnergyCost  = PokemonCard.setAndReturnEnergyArray(0,0,0,0,1,0,0,0,0,0,0);

	List<Ability> abilities = new ArrayList<>();
	AbilitiesFactory abilitiesFactory = new AbilitiesFactory();
	abilities.add(AbilitiesFactory.getAbility("Spacing out"));// Flip a coin if head it clears the damage counter of this pokemon
	abilities.add(AbilitiesFactory.getAbility("Scavenge Discard"));// put a trainer card from the discarded file into9 your hand
	                  //weakness Psychic;
                     //resistance ****;
	PokemonCard SlowpokeGEN = new PokemonCard ("Basic", "Slowpoke", 50, abilities  , retreatEnergyCost);
}


else if(pokemonName=="ZubatGEN")
{
	int[] retreatEnergyCost = new int[11];
	retreatEnergyCost  = PokemonCard.setAndReturnEnergyArray(0,0,0,0,1,0,0,0,0,0,0);

	List<Ability> abilities = new ArrayList<>();
	AbilitiesFactory abilitiesFactory = new AbilitiesFactory();
	abilities.add(AbilitiesFactory.getAbility("Skill Dive"));// 10 damage
	                  //weakness Lightening;
                     //resistance Fighting;
	PokemonCard ZubatGEN = new PokemonCard ("Basic", " Zubat", 50, abilities  , retreatEnergyCost);
}







                         // Following Factory is for deck 2






else if(pokemonName=="GlameowBKP")
{
	int[] retreatEnergyCost = new int[11];
	retreatEnergyCost  = PokemonCard.setAndReturnEnergyArray(1,0,0,0,0,0,0,0,0,0,0);

	List<Ability> abilities = new ArrayList<>();
	AbilitiesFactory abilitiesFactory = new AbilitiesFactory();
	abilities.add(AbilitiesFactory.getAbility("Act Cute"));
	abilities.add(AbilitiesFactory.getAbility("Scratch"));// 20 damage
	                  //weakness Fighting;
                     //resistance ****;
	PokemonCard GlameowGEN = new PokemonCard ("Basic", "Glameow", 60, abilities  , retreatEnergyCost);
}


else if(pokemonName=="PuruglyTK_Pikachulibre")
{
	int[] retreatEnergyCost = new int[11];
	retreatEnergyCost  = PokemonCard.setAndReturnEnergyArray(2,0,0,0,0,0,0,0,0,0,0);

	List<Ability> abilities = new ArrayList<>();
	AbilitiesFactory abilitiesFactory = new AbilitiesFactory();
	abilities.add(AbilitiesFactory.getAbility("Slash"));// 30 damage
	abilities.add(AbilitiesFactory.getAbility("Nyan Press"));// flip a coin if heads 40 damage will bve donbe to your opponents pokemon if tails your opponent pokemon will now be paralyzed
	                  //weakness Fighting;
                     //resistance ****;
	PokemonCard PuruglyTK_Pokemonlibre = new PokemonCard ("Stage-1", "Purugly", 100, abilities  , retreatEnergyCost);
	setEvolvesFrom();
}


else if(pokemonName=="ElectivireTK_Pikachulibre")
{
	int[] retreatEnergyCost = new int[11];
	retreatEnergyCost  = PokemonCard.setAndReturnEnergyArray(0,0,0,2,0,0,0,0,0,0,0);

	List<Ability> abilities = new ArrayList<>();
	AbilitiesFactory abilitiesFactory = new AbilitiesFactory();
	abilities.add(AbilitiesFactory.getAbility("Knuckle Punch"));// 20 damage
	                  //weakness Fighting;
                     //resistance ****;
	PokemonCard ElectabuzzTK_Pokemonlibre = new PokemonCard ("Basic", "Electabuzz", 70, abilities  , retreatEnergyCost);
}

else if(pokemonName=="PuruglyTK_Pikachulibre")
{
	int[] retreatEnergyCost = new int[11];
	retreatEnergyCost  = PokemonCard.setAndReturnEnergyArray(0,0,0,3,0,0,0,0,0,0,0);

	List<Ability> abilities = new ArrayList<>();
	AbilitiesFactory abilitiesFactory = new AbilitiesFactory();
	abilities.add(AbilitiesFactory.getAbility("Knuckle Punch"));// 30 damage
	abilities.add(AbilitiesFactory.getAbility("Electroslug"));// 90 damag
	                  //weakness Fighting;
                     //resistance ****;
	PokemonCard ElectivireTK_Pokemonlibre = new PokemonCard ("Stage-1", "Electivire", 110, abilities  , retreatEnergyCost);
	setEvolvesFrom();
}

else if(pokemonName=="PuruglyTK_Pikachulibre")
{
	int[] retreatEnergyCost = new int[11];
	retreatEnergyCost  = PokemonCard.setAndReturnEnergyArray(2,0,0,0,0,0,0,0,0,0,0);

	List<Ability> abilities = new ArrayList<>();
	AbilitiesFactory abilitiesFactory = new AbilitiesFactory();
	abilities.add(AbilitiesFactory.getAbility("Slash"));// 30 damage
	abilities.add(AbilitiesFactory.getAbility("Nyan Press"));// flip a coin if heads 40 damage will be done to your opponents pokemon if tails your opponent pokemon will now be paralyzed
	                  //weakness Fighting;
                     //resistance ****;
	PokemonCard PuruglyTK_Pokemonlibre = new PokemonCard ("Stage-1", "Purugly", 100, abilities  , retreatEnergyCost);
	setEvolvesFrom();
}

else if(pokemonName=="ElectrikeTK_Pikachulibre")
{
	int[] retreatEnergyCost = new int[11];
	retreatEnergyCost  = PokemonCard.setAndReturnEnergyArray(0,0,0,1,0,0,0,0,0,0,0);

	List<Ability> abilities = new ArrayList<>();
	AbilitiesFactory abilitiesFactory = new AbilitiesFactory();
	abilities.add(AbilitiesFactory.getAbility("Bite"));// 10 damage
	                  //weakness Fighting;
                     //resistance steel;
	PokemonCard ElectrikeTK_Pokemonlibre = new PokemonCard ("Basic", "Electrike", 60, abilities  , retreatEnergyCost);
}


else if(pokemonName=="ManetricTK_Pikachulibre")
{
	int[] retreatEnergyCost = new int[11];
	retreatEnergyCost  = PokemonCard.setAndReturnEnergyArray(0,0,0,1,0,0,0,0,0,0,0);

	List<Ability> abilities = new ArrayList<>();
	AbilitiesFactory abilitiesFactory = new AbilitiesFactory();
	abilities.add(AbilitiesFactory.getAbility("Random Spark"));// 30 damage
	abilities.add(AbilitiesFactory.getAbility("Bite"));// 40 damage
	                  //weakness Fighting;
                     //resistance ****;
	PokemonCard ManetricTK_Pokemonlibre = new PokemonCard ("Stage-1", "Manetric", 90, abilities  , retreatEnergyCost);
	setEvolvesFrom();
}

else if(pokemonName=="HelioptileFLF")
{
	int[] retreatEnergyCost = new int[11];
	retreatEnergyCost  = PokemonCard.setAndReturnEnergyArray(0,0,0,1,0,0,0,0,0,0,0);

	List<Ability> abilities = new ArrayList<>();
	AbilitiesFactory abilitiesFactory = new AbilitiesFactory();
	abilities.add(AbilitiesFactory.getAbility("Pound"));// 10 damage
	abilities.add(AbilitiesFactory.getAbility("Distructive Beam"));// Flip a coin if heads remove an energy card which is attached to your opponent
	                  //weakness Fighting;
                     //resistance Steel;
	PokemonCard HeliotileFLF = new PokemonCard ("Basic", "Helioptile", 60, abilities  , retreatEnergyCost);
}


else if(pokemonName=="PikachuGEN")
{
	int[] retreatEnergyCost = new int[11];
	retreatEnergyCost  = PokemonCard.setAndReturnEnergyArray(0,0,0,1,0,0,0,0,0,0,0);

	List<Ability> abilities = new ArrayList<>();
	AbilitiesFactory abilitiesFactory = new AbilitiesFactory();
	abilities.add(AbilitiesFactory.getAbility("Nuzzle"));// Flip a coin if head active pokemon of your opponent is now paralyzed
	abilities.add(AbilitiesFactory.getAbility("Quick Attack"));// 20 damage flip a coin if heads 10 more damage will be done to your opponents pokemon	                  //weakness Fighting;
	                             //weakness Fighting; 
	                            //resistance Steel;
	PokemonCard PikachuGEN = new PokemonCard ("Basic", "Pikachu", 60, abilities  , retreatEnergyCost);
}


else if(pokemonName=="RaichuGEN")
{
	int[] retreatEnergyCost = new int[11];
	retreatEnergyCost  = PokemonCard.setAndReturnEnergyArray(0,0,0,1,0,0,0,0,0,0,0);

	List<Ability> abilities = new ArrayList<>();
	AbilitiesFactory abilitiesFactory = new AbilitiesFactory();
	abilities.add(AbilitiesFactory.getAbility("Circle Circuit"));// it does the damage 20 times the number of pokemons on the opponents bench 
	abilities.add(AbilitiesFactory.getAbility("Thunderbolt"));// 100 damage
	                  //weakness Fighting;
                     //resistance Metal;
	PokemonCard RaichuGEN = new PokemonCard ("Stage-1", "Raichu", 90, abilities  , retreatEnergyCost);
	setEvolvesFrom();
}


else if(pokemonName=="PikachuTK_Pikachulibre")
{
	int[] retreatEnergyCost = new int[11];
	retreatEnergyCost  = PokemonCard.setAndReturnEnergyArray(0,0,0,1,0,0,0,0,0,0,0);

	List<Ability> abilities = new ArrayList<>();
	AbilitiesFactory abilitiesFactory = new AbilitiesFactory();
	abilities.add(AbilitiesFactory.getAbility("Quick Attack"));// 10 damage, flip a coin if heads this does 30 more damage to the opponent pokemon
	abilities.add(AbilitiesFactory.getAbility("Flying Elekick"));// 50 damage
	                  //weakness Fighting;
                     //resistance Metal;
	PokemonCard PikachuTK_Pokemonlibre = new PokemonCard ("Basic", "Pikachu", 80, abilities  , retreatEnergyCost);
}


else if(pokemonName=="DucklettTK_Suicune")
{
	int[] retreatEnergyCost = new int[11];
	retreatEnergyCost  = PokemonCard.setAndReturnEnergyArray(0,0,1,0,0,0,0,0,0,0,0);

	List<Ability> abilities = new ArrayList<>();
	AbilitiesFactory abilitiesFactory = new AbilitiesFactory();
	abilities.add(AbilitiesFactory.getAbility("Lunge"));// 20 damage flip a coin if tails this attack does nothing
	                  //weakness Lightning;
                     //resistance Fighting;
	PokemonCard DucklettTK_Suicune = new PokemonCard ("Basic", "Ducklett", 60, abilities  , retreatEnergyCost);
}


else if(pokemonName=="SwannaTK_Suicune")
{
	int[] retreatEnergyCost = new int[11];
	retreatEnergyCost  = PokemonCard.setAndReturnEnergyArray(0,0,1,0,0,0,0,0,0,0,0);

	List<Ability> abilities = new ArrayList<>();
	AbilitiesFactory abilitiesFactory = new AbilitiesFactory();
	abilities.add(AbilitiesFactory.getAbility("Wing Attack"));// 20 damage
	abilities.add(AbilitiesFactory.getAbility("Brave Bird"));// his attack does 20 damage to itself
	                  //weakness Lightning;
                     //resistance Fighting;
	PokemonCard SwannaTK_Suicune = new PokemonCard ("Stage-1", "Swanna", 80, abilities  , retreatEnergyCost);
	setEvolvesFrom();
}


else if(pokemonName=="FroakieBKP")
{
	int[] retreatEnergyCost = new int[11];
	retreatEnergyCost  = PokemonCard.setAndReturnEnergyArray(0,0,1,0,0,0,0,0,0,0,0);

	List<Ability> abilities = new ArrayList<>();
	AbilitiesFactory abilitiesFactory = new AbilitiesFactory();
	abilities.add(AbilitiesFactory.getAbility("Pound"));// 10 damage
		                  //weakness Grass;
                     //resistance ***;
	PokemonCard FroakieBKP = new PokemonCard ("Basic", "Froakie", 50, abilities  , retreatEnergyCost);
}



else if(pokemonName=="FrogadierBKP")
{
	int[] retreatEnergyCost = new int[11];
	retreatEnergyCost  = PokemonCard.setAndReturnEnergyArray(0,0,1,0,0,0,0,0,0,0,0);

	List<Ability> abilities = new ArrayList<>();
	AbilitiesFactory abilitiesFactory = new AbilitiesFactory();
	abilities.add(AbilitiesFactory.getAbility("Cut"));// 30 damage
	                  //weakness Grass;
                     //resistance ***;
	PokemonCard FrogadierBKP = new PokemonCard ("Stage-1", "Frogadier", 70, abilities  , retreatEnergyCost);
	setEvolvesFrom();
}


else if(pokemonName=="GoldeenTK_Suicune")
{
	int[] retreatEnergyCost = new int[11];
	retreatEnergyCost  = PokemonCard.setAndReturnEnergyArray(0,0,1,0,0,0,0,0,0,0,0);

	List<Ability> abilities = new ArrayList<>();
	AbilitiesFactory abilitiesFactory = new AbilitiesFactory();
	abilities.add(AbilitiesFactory.getAbility("Reckless Charge"));// 20 damage it does 10 damage to itself
	                  //weakness Grass;
                     //resistance ****;
	PokemonCard GoldeenTK_Suicune = new PokemonCard ("Basic", "Goldeen", 60, abilities  , retreatEnergyCost);
}


else if(pokemonName=="SeakingTK_Suicune")
{
	int[] retreatEnergyCost = new int[11];
	retreatEnergyCost  = PokemonCard.setAndReturnEnergyArray(0,0,2,0,0,0,0,0,0,0,0);

	List<Ability> abilities = new ArrayList<>();
	AbilitiesFactory abilitiesFactory = new AbilitiesFactory();
	abilities.add(AbilitiesFactory.getAbility("Soaking Horn"));// 10 damage if this pokrmon is healed during this battack it does 80 more damage to its opponent
	abilities.add(AbilitiesFactory.getAbility("Reckless Charge"));//  40 damage it does 10 damage to itself
	                  //weakness Grass;
                     //resistance ***;
	PokemonCard SeakingTK_Suicune = new PokemonCard ("Stage-1", "Seaking", 90, abilities  , retreatEnergyCost);
	setEvolvesFrom();
}



else if(pokemonName=="ShellderGEN")
{
	int[] retreatEnergyCost = new int[11];
	retreatEnergyCost  = PokemonCard.setAndReturnEnergyArray(0,0,1,0,0,0,0,0,0,0,0);

	List<Ability> abilities = new ArrayList<>();
	AbilitiesFactory abilitiesFactory = new AbilitiesFactory();
	abilities.add(AbilitiesFactory.getAbility("Rain Splash"));// 20 damage
	                  //weakness Grass;
                     //resistance ***;
	PokemonCard ShellderGEN = new PokemonCard ("Basic", "Shellder", 60, abilities  , retreatEnergyCost);
}



else if(pokemonName=="CloysterGEN")
{
	int[] retreatEnergyCost = new int[11];
	retreatEnergyCost  = PokemonCard.setAndReturnEnergyArray(0,0,3,0,0,0,0,0,0,0,0);

	List<Ability> abilities = new ArrayList<>();
	AbilitiesFactory abilitiesFactory = new AbilitiesFactory();
	abilities.add(AbilitiesFactory.getAbility("Clamp Crush"));// 30 damage.......flip a coin if heads your opponents active pokemon is now paralyzed and the energy card attached to it is now discarded
	abilities.add(AbilitiesFactory.getAbility("Spike Cannon"));// flip 5 coins this bdoes 30 more damage times the number of heads.......30 damage for tails
	                  //weakness Grass;
                     //resistance ****;
	PokemonCard CloysterGEN = new PokemonCard ("Stage-1", "Cloyster", 100, abilities  , retreatEnergyCost);
	setEvolvesFrom();
}


else if(pokemonName=="SuicuneTK_Suicune")
{
	int[] retreatEnergyCost = new int[11];
	retreatEnergyCost  = PokemonCard.setAndReturnEnergyArray(0,0,1,0,0,0,0,0,0,0,0);

	List<Ability> abilities = new ArrayList<>();
	AbilitiesFactory abilitiesFactory = new AbilitiesFactory();
	abilities.add(AbilitiesFactory.getAbility("Spiral Drain"));// heal 20 damage
	abilities.add(AbilitiesFactory.getAbility("Aurora Beam"));// 80 damage
	                  //weakness Lightning;
                     //resistance Fighting;
	PokemonCard SuicuneTK_Suicune = new PokemonCard ("Basic", "Suicune", 100, abilities  , retreatEnergyCost);
	setEvolvesFrom();
}
	}
}




