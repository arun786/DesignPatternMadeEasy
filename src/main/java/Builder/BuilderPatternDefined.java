package Builder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class BuilderPatternDefined {

    public static void main(String[] args) {
        Player player = PlayerBuilder.newInstance().setId("1").setName("Arun").build();
        System.out.println(player);
    }
}

@ToString
class Player {
    private String id;
    private String name;

    public Player(PlayerBuilder playerBuilder) {
        this.id = playerBuilder.getId();
        this.name = playerBuilder.getName();
    }
}

@Getter
@Setter
class PlayerBuilder {
    private String id;
    private String name;

    private PlayerBuilder() {
    }

    public static PlayerBuilder newInstance() {
        return new PlayerBuilder();
    }

    public PlayerBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public PlayerBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public Player build() {
        return new Player(this);
    }
}
