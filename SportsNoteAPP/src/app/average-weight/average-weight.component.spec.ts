import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AverageWeightComponent } from './average-weight.component';

describe('AverageWeightComponent', () => {
  let component: AverageWeightComponent;
  let fixture: ComponentFixture<AverageWeightComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AverageWeightComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AverageWeightComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
