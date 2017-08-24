package com.rewardsnetwork.client;

import java.util.NoSuchElementException;
import java.util.Scanner;

import com.rewardsnetwork.main.GetDressed;
import com.rewardsnetwork.main.IGetDressed;

public class GetDressedClient {

	public static void main(String[] args) {

		final Scanner sc = new Scanner(System.in);

		try {

			System.out.println("Please enter the command set by weather type to get dressed and hit enter - ");

			String input = sc.nextLine();

			final IGetDressed getDressed = new GetDressed();

			String dressUpOrderResponse = getDressed.dressUp(input);

			System.out.println(dressUpOrderResponse);

		} catch (NoSuchElementException | IllegalStateException ex) {

			System.out.print(ex);

		} finally {

			sc.close();

		}

	}

}
