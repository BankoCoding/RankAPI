package de.Banko.RankAPI.Groups;

public class BoardTeam {

	String name;
	String prefix;
	String suffix;

	String entry;
	int score;

	public BoardTeam(String name, String prefix, String suffix, String entry, int score) {
		super();
		this.name = name;
		this.prefix = prefix;
		this.suffix = suffix;
		this.entry = entry;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getEntry() {
		return entry;
	}

	public void setEntry(String entry) {
		this.entry = entry;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
