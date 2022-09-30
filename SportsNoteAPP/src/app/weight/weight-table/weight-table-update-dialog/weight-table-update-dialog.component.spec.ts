import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WeightTableUpdateDialogComponent } from './weight-table-update-dialog.component';

describe('WeightTableUpdateDialogComponent', () => {
  let component: WeightTableUpdateDialogComponent;
  let fixture: ComponentFixture<WeightTableUpdateDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WeightTableUpdateDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WeightTableUpdateDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
