import {Injectable} from '@angular/core';

import {HttpClient} from '@angular/common/http';

import {Movie} from './movie.model';

import {Observable} from 'rxjs';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import {MovieWithActors} from './movieWithActors.model';
import {Actor} from './actor.model';


@Injectable()
export class MovieService {
  private moviesUrl = 'http://localhost:8080/api/movies';
  private moviesActorsUrl = 'http://localhost:8080/api/moviesWithActors';
  private deleteUrl = 'http://localhost:8080/api/delete';

  constructor(private httpClient: HttpClient) {
  }

  getMovies(): Observable<Movie[]> {
    return this.httpClient
      .get<Array<Movie>>(this.moviesUrl);
  }
  getMoviesBefore(year): Observable<Movie[]> {
    return this.httpClient
      .get<Array<Movie>>(this.moviesUrl + '/' + year + '/' + 1);
  }

  getMoviesAfter(year): Observable<Movie[]> {
    return this.httpClient
      .get<Array<Movie>>(this.moviesUrl + '/' + year + '/' + 2);
  }

  getMovieWithActors(id: number): Observable<MovieWithActors> {
    return this.httpClient
      .get<MovieWithActors>(`${this.moviesUrl}/${id}`);
  }

  getAvailableActors(): Observable<Actor[]> {
    return this.httpClient
      .get<Array<Actor>>('http://localhost:8080/api/availableActors');
  }

  addActor(movieId: number, actorId: number): Observable<Movie> {
    return this.httpClient.put<Movie>(`http://localhost:8080/api/movies/${movieId}/${actorId}`, null);
  }

  //
  // getStudent(id: number): Observable<Movie> {
  //   return this.getStudents()
  //     .map(students => students.find(student => student.id === id));
  // }
  //
  // update(student): Observable<Movie> {
  //   const url = `${this.studentsUrl}/${student.id}`;
  //   return this.httpClient
  //     .put<Movie>(url, student);
  // }
  getMoviesWithActorsBefore(year: any) {
    return this.httpClient
      .get<Array<MovieWithActors>>(this.moviesActorsUrl + '/' + year + '/' + 1);
  }

  getMoviesWithActorsAfter(year: any) {
    return this.httpClient
      .get<Array<MovieWithActors>>(this.moviesActorsUrl + '/' + year + '/' + 2);
  }


  deleteActor(id: any, movieId: any) {
    console.log(this.deleteUrl);
    return this.httpClient.delete(this.deleteUrl  + '/' + id + '/' + movieId );
  }
}
