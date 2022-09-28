package com.example.test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
	
	public static void main(String[] args) throws IOException, URISyntaxException {
		
		List<Light> lights = readInput();
		
		int maxAdjacentCount = -1;
		int bestStep = -1;
		
		int currentStep = 0;
		
		for (int i = 0; i < 20000; i++) {
			
			lights.forEach(l -> l.move(1));
			currentStep++;
			int adjacentCount = 0;
			
			for (Light l1 : lights) {
				for (Light l2 : lights) {
					if (l1 != l2 && Math.abs(l1.pos[0] - l2.pos[0]) <= 1 && Math.abs(l1.pos[1] - l2.pos[1]) <= 1) {
						adjacentCount++;
						break;
					}
				}
			}
		
			if (maxAdjacentCount < adjacentCount) {
				maxAdjacentCount = adjacentCount;
				bestStep = currentStep;

				if (maxAdjacentCount == lights.size()) {
					break;
				}
			}
			
		}
		
		lights = readInput();
		int stepsUntilMessage = bestStep;
		lights.forEach(l -> l.move(stepsUntilMessage));
		
		print(lights);
		System.out.println("\n @ " + stepsUntilMessage + " seconds");
	}
	
	public static List<Light> readInput() throws IOException, URISyntaxException {
		try (Stream<String> lines = Files.lines(Path.of(ClassLoader.getSystemResource("input.txt").toURI()))) {
			return lines.map(Light::of).collect(Collectors.toList());
		}
	}
	
	public static void print(List<Light> lights) {
		int minX = lights.stream().mapToInt(l -> l.pos[0]).min().getAsInt();
		int maxX = lights.stream().mapToInt(l -> l.pos[0]).max().getAsInt();

		int minY = lights.stream().mapToInt(l -> l.pos[1]).min().getAsInt();
		int maxY = lights.stream().mapToInt(l -> l.pos[1]).max().getAsInt();
		
		int[][] grid = new int[1 + maxY - minY][1 + maxX - minX];
		
		for (Light light : lights) {
			grid[light.pos[1] - minY][light.pos[0] - minX] = 1; // Flip x & y
		}
		
		Arrays.stream(grid).map(row -> Arrays.stream(row).mapToObj(x -> x == 1 ? "#" : ".").collect(Collectors.joining()))
			.forEach(System.out::println);
	}

}
