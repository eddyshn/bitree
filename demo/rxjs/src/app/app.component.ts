import {Component} from '@angular/core';
import {map, take, flatMap, switchMap} from 'rxjs/operators';
import {Observable, interval, of, timer} from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'rxjs-project';
  selectedId = 0;
  data = [
    {id: 1, name: 'map', checked: false, run: () => this.map()},
    {id: 2, name: 'flatMap/mergeMap', checked: false, run: () => this.flatMap()},
    {id: 3, name: 'swithMap', checked: false, run: () => this.switchMap()},
    {id: 4, name: 'Observable.create', checked: false, run: () => this.create()}
  ];

  run() {
    console.clear();
    const item = this.data.find(x => x.id == this.selectedId);
    if (item) {
      item.run();
    }
  }


  map() {
    console.log('this is map example');

    const source = interval(1000)
      .pipe(
        take(5),
        map(x => x * 2)
      );
    source.subscribe(x => console.log(x));//0,2,4,6,8
  }

  flatMap() {
    console.log('this is flatMap example');

    const source = interval(1000)
      .pipe(
        take(5),
        //map(()=>of(11))
        flatMap(() => of(11))//11,11,11,11,11
      );

    source.subscribe(x => console.log(x));
  }

  switchMap() {

    // 立即发出值， 然后每5秒发出值
    const source = timer(0, 5000);
    // 当 source 发出值时切换到新的内部 observable，发出新的内部 observable 所发出的值,取消以前的订阅
    const example = source.pipe(switchMap(() => interval(500)));
    // 输出: 0,1,2,3,4,5,6,7,8,9...0,1,2,3,4,5,6,7,8
    const subscribe = example.subscribe(val => console.log(val));
  }

  create() {
    const observable = Observable.create(subscriber => {
      var i = 0;
      setInterval(() => {
        subscriber.next(i++);
      }, 5000);
    });

    observable.subscribe(x => {
      console.log("get " + x);
    });
  }
}
