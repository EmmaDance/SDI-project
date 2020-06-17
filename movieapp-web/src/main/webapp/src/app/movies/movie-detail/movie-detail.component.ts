import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Params} from "@angular/router";
import {Location} from '@angular/common';


import 'rxjs/add/operator/switchMap';
import {MovieService} from "../shared/movie.service";
import {MovieWithActors} from "../shared/movieWithActors.model";
import {Actor} from "../shared/actor.model";


@Component({
    selector: 'movie-detail',
    templateUrl: './student-detail.component.html',
    styleUrls: ['./student-detail.component.css'],
})

export class MovieDetailComponent implements OnInit {

    movie: MovieWithActors;
    addMoreB: boolean;

    actors: Actor[];

    selectedActor: Actor;

    constructor(private movieService: MovieService,
                private route: ActivatedRoute,
                private location: Location) {
    }

    ngOnInit(): void {
      this.addMoreB = false;
        this.route.params
            .switchMap((params: Params) => this.movieService.getMovieWithActors(+params['id']))
            .subscribe(movie => this.movie = movie);

        this.movieService.getAvailableActors().subscribe(actors => this.actors = actors);

    }

    goBack(): void {
        this.location.back();
    }

    // save(): void {
    //     this.movieService.update(this.movie)
    //         .subscribe(_ => this.goBack());
    // }
  addMore() {
    this.addMoreB = true;
  }

  add() {
      this.movie.actors.push(this.selectedActor);
      this.actors = this.actors.filter(a => a.id != this.selectedActor.id);
      this.movieService.addActor(this.movie.id, this.selectedActor.id).subscribe();
  }
}
