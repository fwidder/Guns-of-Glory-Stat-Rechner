package com.github.fwidder.statrechner.controller.web;

import com.github.fwidder.statrechner.dao.PlayerRepository;
import com.github.fwidder.statrechner.model.Player;
import com.github.fwidder.statrechner.service.ChartService;
import com.github.fwidder.statrechner.util.ObjectNotFoundException;
import org.apache.pdfbox.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Controller
@RequestMapping("/api/charts")
public class ChartController {

    @Autowired
    private ChartService chartService;

    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping(value = "/{playerId}/chart.png", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> chartForPlayer(@PathVariable Long playerId) throws IOException {
        Optional<Player> player = playerRepository.findById(playerId);
        if (player.isEmpty())
            throw new ObjectNotFoundException("Player with ID " + playerId + " does not exist!");

        File file = chartService.createResourceChartForPlayer(player.get());
        InputStream in = new FileInputStream(file);
        byte[] media = IOUtils.toByteArray(in);

        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());

        return new ResponseEntity<>(media, headers, HttpStatus.OK);
    }
}
