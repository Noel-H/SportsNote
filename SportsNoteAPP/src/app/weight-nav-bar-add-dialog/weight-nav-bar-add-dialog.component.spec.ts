import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WeightNavBarAddDialogComponent } from './weight-nav-bar-add-dialog.component';

describe('WeightNavBarAddDialogComponent', () => {
  let component: WeightNavBarAddDialogComponent;
  let fixture: ComponentFixture<WeightNavBarAddDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WeightNavBarAddDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WeightNavBarAddDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
