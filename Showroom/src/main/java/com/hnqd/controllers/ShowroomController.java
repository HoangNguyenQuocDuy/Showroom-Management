/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.controllers;

import com.hnqd.dto.ShowroomResponse;
import com.hnqd.pojo.Showroom;
import com.hnqd.services.ShowroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author DELL
 */
@RestController
@RequestMapping("api/v1/showrooms")
public class ShowroomController {

    @Autowired
    private ShowroomService showroomService;

    @PostMapping("/create/")
    public ResponseEntity createShowroom(@RequestBody Showroom showroom) {
        try {
            showroomService.addShowroom(showroom);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                    "Create Showroom successfully!"
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    "Failed when create showroom " + e.getMessage()
            );
        }
    }

    @GetMapping("/")
    public ResponseEntity getAllShowrooms() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    showroomService.getShowrooms()
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    "Failed when get showrooms " + e.getMessage()
            );
        }
    }

    @GetMapping("/{showroomId}")
    public ResponseEntity getAllShowroom(@PathVariable("showroomId") int showroomId) {
        try {
            ShowroomResponse showroomFind = showroomService.getShowroomById(showroomId);

            if (showroomFind == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        "Showroom not found!"
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    showroomFind
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    "Failed when get showroom by id " + e.getMessage()
            );
        }
    }

    @PatchMapping("/update/")
    public ResponseEntity updateShowroomById(@RequestBody Showroom showroom) {
        try {
            ShowroomResponse showroomFind = showroomService.getShowroomById(showroom.getId());

            if (showroomFind == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        "Showroom not found!"
                );
            }

            return ResponseEntity.status(HttpStatus.OK).body(
                    showroomService.updateShowroomById(showroom.getId(), showroom)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    "Failed when update showroom " + e.getMessage()
            );
        }
    }

    @DeleteMapping("/delete/{showroomId}}")
    public ResponseEntity deleteShowroomById(@PathVariable int showroomId) {
        try {
            ShowroomResponse showroomFind = showroomService.getShowroomById(showroomId);

            if (showroomFind == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        "Showroom not found!"
                );
            }
            showroomService.deleteShowroomById(showroomId);

            return ResponseEntity.status(HttpStatus.OK).body(
                    "Delete Showroom successful!"
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    "Failed when delete showroom " + e.getMessage()
            );
        }
    }
}
