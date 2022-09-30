import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WeightNavBarComponent } from './weight-nav-bar.component';

describe('WeightNavBarComponent', () => {
  let component: WeightNavBarComponent;
  let fixture: ComponentFixture<WeightNavBarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WeightNavBarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WeightNavBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
