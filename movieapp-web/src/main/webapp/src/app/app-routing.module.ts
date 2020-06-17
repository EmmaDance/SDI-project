import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {StudentsComponent} from './students/students.component';
import {StudentDetailComponent} from './students/student-detail/student-detail.component';
import {MovieListComponent} from './movies/movie-list/movie-list.component';
import {MovieDetailComponent} from './movies/movie-detail/movie-detail.component';


const routes: Routes = [
  // { path: '', redirectTo: '/home', pathMatch: 'full' },
  {path: 'students', component: StudentsComponent},
  {path: 'student/detail/:id', component: StudentDetailComponent},
  {path: 'movieapp/movies', component: MovieListComponent},
  {path: 'movieapp/movie/details/:id', component: MovieDetailComponent},


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
