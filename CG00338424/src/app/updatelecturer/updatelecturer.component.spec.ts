import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateLecturerComponent } from './updatelecturer.component';

describe('UpdatelecturerComponent', () => {
  let component: UpdateLecturerComponent;
  let fixture: ComponentFixture<UpdateLecturerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UpdateLecturerComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateLecturerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
