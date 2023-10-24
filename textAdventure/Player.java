public class Player {
    private int hp;
    private int attackDamage;
    private int defeatedGoblins;


    public Player(int initialHP, int initialAttackDamage) {
        this.hp = initialHP;
        this.attackDamage = initialAttackDamage;
        this.defeatedGoblins = 0;
    }

    public void train() {
        attackDamage += 5;
    }
    public void recover(){
        if(hp==100){
        }
        else{
            hp+=10;
        }
    }
    public int getDefeatedGoblins() {
        return defeatedGoblins;
    }
    public void defeatGoblin() {
        defeatedGoblins++;
    }

    public int getHP() {
        return hp;
    }

    public int getAttackDamage() {
        return attackDamage;
    }
    public void takeDamage(int damage) {
        hp -= damage;
        if (hp < 0) {
            hp = 0;
        }
    }
    
}
