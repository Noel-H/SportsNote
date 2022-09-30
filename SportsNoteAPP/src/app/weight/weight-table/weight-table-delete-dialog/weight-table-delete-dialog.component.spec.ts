import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WeightTableDeleteDialogComponent } from './weight-table-delete-dialog.component';

describe('WeightTableDeleteDialogComponent', () => {
  let component: WeightTableDeleteDialogComponent;
  let fixture: ComponentFixture<WeightTableDeleteDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WeightTableDeleteDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WeightTableDeleteDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
