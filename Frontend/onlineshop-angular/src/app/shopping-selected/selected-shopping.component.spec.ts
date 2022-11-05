import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectedShoppingComponent } from './selected-shopping.component';

describe('SelectedShoppingComponent', () => {
  let component: SelectedShoppingComponent;
  let fixture: ComponentFixture<SelectedShoppingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SelectedShoppingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SelectedShoppingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
