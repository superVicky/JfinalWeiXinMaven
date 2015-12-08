package com.javen.weixin.entity;

/**
 * @author Javen
 * @Email javenlife@126.com
 * 
 */
public class BaiduMovieEntity {
	private String movie_id;
	private String movie_name;
	private String movie_type;
	private String movie_release_date;
	private String movie_nation;
	private String movie_starring;
	private String movie_length;
	private String movie_picture;
	private String movie_score;
	private String movie_director;
	private String movie_tags;
	private String movie_message;
	private String is_new;
	private String movies_wd;
	
	
	
	public BaiduMovieEntity() {
	}
	
	public BaiduMovieEntity(String movie_id, String movie_name,
			String movie_type, String movie_release_date, String movie_nation,
			String movie_starring, String movie_length, String movie_picture,
			String movie_score, String movie_director, String movie_tags,
			String movie_message, String is_new) {
		
		this.movie_id = movie_id;
		this.movie_name = movie_name;
		this.movie_type = movie_type;
		this.movie_release_date = movie_release_date;
		this.movie_nation = movie_nation;
		this.movie_starring = movie_starring;
		this.movie_length = movie_length;
		this.movie_picture = movie_picture;
		this.movie_score = movie_score;
		this.movie_director = movie_director;
		this.movie_tags = movie_tags;
		this.movie_message = movie_message;
		this.is_new = is_new;
		this.movies_wd = movies_wd;
	}

	public String getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(String movie_id) {
		this.movie_id = movie_id;
	}
	public String getMovie_name() {
		return movie_name;
	}
	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}
	public String getMovie_type() {
		return movie_type;
	}
	public void setMovie_type(String movie_type) {
		this.movie_type = movie_type;
	}
	public String getMovie_release_date() {
		return movie_release_date;
	}
	public void setMovie_release_date(String movie_release_date) {
		this.movie_release_date = movie_release_date;
	}
	public String getMovie_nation() {
		return movie_nation;
	}
	public void setMovie_nation(String movie_nation) {
		this.movie_nation = movie_nation;
	}
	public String getMovie_starring() {
		return movie_starring;
	}
	public void setMovie_starring(String movie_starring) {
		this.movie_starring = movie_starring;
	}
	public String getMovie_length() {
		return movie_length;
	}
	public void setMovie_length(String movie_length) {
		this.movie_length = movie_length;
	}
	public String getMovie_picture() {
		return movie_picture;
	}
	public void setMovie_picture(String movie_picture) {
		this.movie_picture = movie_picture;
	}
	public String getMovie_score() {
		return movie_score;
	}
	public void setMovie_score(String movie_score) {
		this.movie_score = movie_score;
	}
	public String getMovie_director() {
		return movie_director;
	}
	public void setMovie_director(String movie_director) {
		this.movie_director = movie_director;
	}
	public String getMovie_tags() {
		return movie_tags;
	}
	public void setMovie_tags(String movie_tags) {
		this.movie_tags = movie_tags;
	}
	public String getMovie_message() {
		return movie_message;
	}
	public void setMovie_message(String movie_message) {
		this.movie_message = movie_message;
	}
	public String getIs_new() {
		return is_new;
	}
	public void setIs_new(String is_new) {
		this.is_new = is_new;
	}
	public String getMovies_wd() {
		return movies_wd;
	}
	public void setMovies_wd(String movies_wd) {
		this.movies_wd = movies_wd;
	}
}
