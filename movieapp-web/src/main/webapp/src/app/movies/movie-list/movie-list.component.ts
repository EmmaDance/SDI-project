import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Movie} from '../shared/movie.model';
import {MovieService} from '../shared/movie.service';
import {MovieWithActors} from '../shared/movieWithActors.model';

@Component({
  moduleId: module.id,
  selector: 'movie-list',
  templateUrl: './movie-list.component.html',
  styleUrls: ['./movie-list.component.css'],
})
export class MovieListComponent implements OnInit {
  errorMessage: string;
  movies: Array<Movie>;
  selectedMovie: Movie;

  visible = true;
  visibleMovies = false;
  movieYear: any;
  comparator: any;
  visibleCast = false;
  moviesWithActors: Array<MovieWithActors>;


  constructor(private movieService: MovieService,
              private router: Router) {
  }

  ngOnInit(): void {
    // this.getStudents();
  }

  // getMovies() {
  //   if (this.comparator === 1) {
  //     this.movieService.getMovies()
  //       .subscribe(
  //         movies => this.movies = movies.filter(movie => movie.year < this.movieYear),
  //         error => this.errorMessage = <any>error
  //       );
  //   } else {
  //     this.movieService.getMovies()
  //       .subscribe(
  //         movies => this.movies = movies.filter(movie => movie.year > this.movieYear),
  //         error => this.errorMessage = <any>error
  //       );
  //   }
  //
  // }

  getMovies() {
    if (this.comparator === '1') {
      console.log('1');
      this.movieService.getMoviesBefore(this.movieYear)
        .subscribe(
          movies => {
            this.movies = movies.sort((a, b) => {
              return b.year - a.year;
            });
            console.log(movies);
          },
          error => this.errorMessage = <any>error
        );
    } else {
      console.log('2');
      this.movieService.getMoviesAfter(this.movieYear)
        .subscribe(
          movies => {
            this.movies = movies.sort((a, b) => {
              return b.year - a.year;
            });
            console.log(movies);
          },
          error => this.errorMessage = <any>error
        );
    }

  }

  getMoviesWithActors() {
    if (this.comparator === '1') {
      console.log('1');
      this.movieService.getMoviesWithActorsBefore(this.movieYear)
        .subscribe(
          movies => {
            this.moviesWithActors = movies.sort((a, b) => {
              return b.year - a.year;
            });
            console.log(movies);
          },
          error => this.errorMessage = <any>error
        );
    } else {
      console.log('2');
      this.movieService.getMoviesWithActorsAfter(this.movieYear)
        .subscribe(
          movies => {
            this.moviesWithActors = movies.sort((a, b) => {
              return b.year - a.year;
            });
            console.log(movies);
          },
          error => this.errorMessage = <any>error
        );
    }

  }

  showMovies() {
    this.getMovies();
    this.visibleMovies = true;
  }

  showCast() {
    this.getMoviesWithActors();
    this.visibleMovies = false;
    this.visibleCast = true;
  }

  delete(actor) {
    console.log(actor);
    this.movieService.deleteActor(actor.id, actor.movieId).subscribe(data => this.showCast());
  }
}
