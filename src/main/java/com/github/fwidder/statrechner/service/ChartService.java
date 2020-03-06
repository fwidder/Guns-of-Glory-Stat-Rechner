package com.github.fwidder.statrechner.service;

import com.github.fwidder.statrechner.dao.PlayerResourceRepository;
import com.github.fwidder.statrechner.model.Player;
import com.github.fwidder.statrechner.model.PlayerResources;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ChartService {

    @Autowired
    private PlayerResourceRepository playerResourceRepository;

    public File createResourceChartForPlayer(Player player) throws IOException {
        File file = File.createTempFile("TMP_ResourceChartForPlayer_" + player.getName() + "_", "_.png");
        List<Long> goldHistory = new ArrayList<>();
        List<Long> wheatHistory = new ArrayList<>();
        List<Long> woodHistory = new ArrayList<>();
        List<Date> timeHistory = new ArrayList<>();

        Iterable<PlayerResources> history = playerResourceRepository.findByPlayerOrderByTimestampAsc(player);

        history.forEach(playerResources -> {
            goldHistory.add(playerResources.getGold());
            wheatHistory.add(playerResources.getWheat());
            woodHistory.add(playerResources.getWood());
            timeHistory.add(Date.from(playerResources.getTimestamp().toInstant(ZoneOffset.UTC)));
        });

        createHistoryChart(player, file, goldHistory, wheatHistory, woodHistory, timeHistory);

        return file;
    }

    private void createHistoryChart(Player player, File file, List<Long> goldHistory, List<Long> wheatHistory, List<Long> woodHistory, List<Date> timeHistory) {
        // Create Chart
        XYChart chart = new XYChartBuilder().width(800).height(600).title("Resourcen von " + player.getName()).xAxisTitle("Zeit").yAxisTitle("Menge").theme(Styler.ChartTheme.XChart).build();

        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.getStyler().setAxisTitlesVisible(false);
        chart.getStyler().setDatePattern("yyyy-MM-dd HH:mm");
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);

        // Series
        chart.addSeries("Gold", timeHistory, goldHistory);
        chart.addSeries("Weizen", timeHistory, wheatHistory);
        chart.addSeries("Holz", timeHistory, woodHistory);

        BitmapEncoder.saveBitmap(chart, file.getAbsolutePath(), BitmapEncoder.BitmapFormat.PNG);
    }
}